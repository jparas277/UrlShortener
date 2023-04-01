package com.project.URLShortenerJava.Service;


import java.time.LocalDate;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.URLShortenerJava.Bean.RequestUrl;
import com.project.URLShortenerJava.Bean.UrlDto;
import com.project.URLShortenerJava.Bean.UrlReportDto;
import com.project.URLShortenerJava.Bean.UserDto;
import com.project.URLShortenerJava.Exception.InvalidDateException;
import com.project.URLShortenerJava.Exception.InvalidLongUrlException;
import com.project.URLShortenerJava.Exception.InvalidUserIdException;
import com.project.URLShortenerJava.Exception.NoDataExistsException;
import com.project.URLShortenerJava.Exception.ShortUrlNotFoundException;
import com.project.URLShortenerJava.Exception.UrlAlreadyExistsException;
import com.project.URLShortenerJava.Repository.UrlReportRepository;
import com.project.URLShortenerJava.Repository.UrlRepository;
import com.project.URLShortenerJava.Repository.UserRepository;
import com.project.URLShortenerJava.Util.AppUtil;
import com.project.URLShortenerJava.Util.HashIdUtil;
import com.project.URLShortenerJava.Util.UserIdUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UrlService {
	
//	@Value("${application.domain}")
	private String domain = "http://localhost:8080/";
//	@Value("${application.threshold}")
	private Long threshold = 10L;
	@Autowired
	private UrlRepository urlRepository;
	@Autowired
	private UrlReportRepository urlReportRepository;
	@Autowired
	private UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(UrlService.class);
	
	public Mono<String> generateShortUrl(RequestUrl request) {
		return Mono.just(request)
					.filter(userData -> (!userData.getUrl().contains(domain) && UrlValidator.getInstance().isValid(userData.getUrl())))
					.switchIfEmpty(Mono.error(new InvalidLongUrlException("Please enter a valid Url")))
					.flatMap(userData -> mapRequestUrlToUserDto(userData))
					.flatMap(userDtoData -> {
						UrlDto urlDto = generateUrl(userDtoData);
						UrlReportDto urlReportDto = generateReport(urlDto);
						Mono<UrlDto> urlDtoResponse = saveToUrlRepository(urlDto);
						Mono<UrlReportDto> urlReportDtoResponse = saveToUrlReportRepository(urlReportDto);
						return Mono.zip(urlDtoResponse, urlReportDtoResponse)
						.map(zippedResponse -> zippedResponse.getT1().getShortUrl());
					});	
		
	}
	
	public Mono<UserDto> mapRequestUrlToUserDto(RequestUrl userData) {
		return Mono.just(userData)
				.flatMap(user -> {
					if(user.getUserId().isEmpty()) {
						return Mono.just(new UserDto())
								.map(userDto -> {
									userDto.setUrl(userData.getUrl());
									userDto.setUserId(UserIdUtil.gen());
									return userDto;
								}).flatMap(userDto -> saveToUserRepository(userDto)
										.then(Mono.just(userDto))).doOnNext(System.out::println);
					} else {
						return Mono.just(new UserDto())
								.map(userDto -> {
									userDto.setUrl(userData.getUrl());
									userDto.setUserId(userData.getUserId().get());
									return userDto;
								})
								.flatMap(userDto -> userRepository.findByUserId(userDto.getUserId())
										.switchIfEmpty(Mono.error(new InvalidUserIdException("Invalid UserId")))
										.then(Mono.just(userDto)))
								.flatMap(userDto -> urlRepository
										.findByUserIdAndLongUrl(userDto.getUserId(), userDto.getUrl())
										.map(AppUtil::urlEntityToDto)
										.flatMap(existingUrl -> Mono.error(new UrlAlreadyExistsException("Short Url Already Exists for this URL",existingUrl.getShortUrl())))
										.then(Mono.just(userDto)));
					}
						
				});
	}
	
	public Mono<UserDto> saveToUserRepository(UserDto userDto){
		return userRepository.save(AppUtil.userDtoToEntity(userDto))
				.map(AppUtil::userEntityToDto);
	}
	
	public UrlDto generateUrl(UserDto userDtoData){
		return new UrlDto(domain + HashIdUtil.encode(),userDtoData.getUrl(),0L,userDtoData.getUserId());
	}
	
	public Mono<UrlDto> saveToUrlRepository(UrlDto urlDto) {
		return urlRepository.save(AppUtil.urlDtoToEntity(urlDto))
				.map(AppUtil::urlEntityToDto);
	}
	
	
	public Mono<UrlReportDto> saveToUrlReportRepository(UrlReportDto urlReportDto) {
		return urlReportRepository.save(AppUtil.urlReportDtoToEntity(urlReportDto))
				.map(AppUtil::urlReportEntityToDto);
	}
	
	public UrlReportDto generateReport(UrlDto urlDto) {
		return new UrlReportDto(urlDto.getCreateDate(),0L,urlDto.getShortUrl(),urlDto.getUserId());
	}
	
	public Mono<String> getLongUrl(String shortUrl) {
		logger.info("getLongUrl Hit with shortUrl " + shortUrl);
		return Mono.just(shortUrl)	
				.flatMap(url -> urlRepository.findByShortUrl(domain + url)
						.map(AppUtil::urlEntityToDto))
				.switchIfEmpty(Mono.error(new ShortUrlNotFoundException("No Page Found")))
				.flatMap(urlData -> updateUrl(urlData))
				.flatMap(urlData -> updateElseCreateUrlReport(urlData)
						.then(Mono.just(urlData)))
				.map(UrlDto::getLongUrl);
	}
	
	public Mono<UrlDto> updateUrl(UrlDto url){
		return Mono.just(url)
				.doOnNext(urlData -> urlData.setClicks(urlData.getClicks() + 1))
				.flatMap(urlData -> {
					if(urlData.getClicks() < threshold) {
						return urlRepository
								.save(AppUtil.urlDtoToEntity(urlData))
								.map(AppUtil::urlEntityToDto);
					} else {
						return urlRepository
								.delete(AppUtil.urlDtoToEntity(urlData))
								.then(Mono.just(urlData));
					}
				});
		 
	}
	
	public Mono<UrlReportDto> updateElseCreateUrlReport(UrlDto urlData){
		return urlReportRepository
				.findByShortUrlAndFetchDate(urlData.getShortUrl(), LocalDate.now())
				.map(AppUtil::urlReportEntityToDto)
				.defaultIfEmpty(generateReport(urlData))
				.doOnNext(System.out::println)
				.map(urlReportData -> {
					urlReportData.setClicks(urlReportData.getClicks() + 1);
					return urlReportData;
				})
				.flatMap(urlDtoData -> urlReportRepository
						.save(AppUtil.urlReportDtoToEntity(urlDtoData))
						.map(AppUtil::urlReportEntityToDto));
	}
	
	
	
	
	public Flux<UrlReportDto> getReportByDate(String date) {
		if(!GenericValidator.isDate(date,"yyyy-MM-dd",true))
			return Flux.error(new InvalidDateException(date + " is not a Valid Date Format"));
		else {
			return urlReportRepository
					.findByFetchDateAndClicksGreaterThan(LocalDate.parse(date),0)
					.map(AppUtil::urlReportEntityToDto)
					.switchIfEmpty(Mono.error(new NoDataExistsException("No data exists for " + date)));
		}
	}
	
	public Flux<UrlReportDto> getReportAll() {
		return urlReportRepository
				.findByClicksGreaterThan(0)
				.map(AppUtil::urlReportEntityToDto)
				.switchIfEmpty(Mono.error(new NoDataExistsException("No data exists")));
	}
	
	public Flux<UrlReportDto> getGeneratedUrlReportByDate(String date) {
		if(!GenericValidator.isDate(date,"yyyy-MM-dd",true))
			return Flux.error(new InvalidDateException(date + " is not a Valid Date Format"));
		else {
			return urlReportRepository
					.findByCreateDate(LocalDate.parse(date))
					.map(AppUtil::urlReportEntityToDto)
					.switchIfEmpty(Mono.error(new NoDataExistsException("No data exists for " + date)));
		}
	}
	
	public Flux<UrlReportDto> getGeneratedUrlReportAll() {
		return urlReportRepository
				.findAll()
				.map(AppUtil::urlReportEntityToDto)
				.switchIfEmpty(Mono.error(new NoDataExistsException("No data exists")));
	}

	public String hello() {
		return threshold + domain;
	}
}
