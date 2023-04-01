package com.project.URLShortenerJava.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.URLShortenerJava.Bean.RequestUrl;
import com.project.URLShortenerJava.Bean.UrlEntity;
import com.project.URLShortenerJava.Bean.UrlReportEntity;
import com.project.URLShortenerJava.Bean.UserEntity;
import com.project.URLShortenerJava.Exception.InvalidLongUrlException;
import com.project.URLShortenerJava.Exception.InvalidUserIdException;
import com.project.URLShortenerJava.Exception.UrlAlreadyExistsException;
import com.project.URLShortenerJava.Repository.UrlReportRepository;
import com.project.URLShortenerJava.Repository.UrlRepository;
import com.project.URLShortenerJava.Repository.UserRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UrlServicePostUrlTest {
	
	@Mock
	private UrlRepository urlRepository;
	
	@Mock
	private UrlReportRepository urlReportRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UrlService service;
	
	
	@Test
	public void postonSuccessTest() {
		String mockLongUrl = "http://google.com";
		String mockShortUrl = "http://localhost:8080/abc";
		UrlEntity mockEntity = new UrlEntity("1",mockShortUrl,mockLongUrl,0L,LocalDate.now(), "Milind");
		UrlReportEntity mockReportEntity = new UrlReportEntity("1",LocalDate.now(),LocalDate.now(),0L,mockShortUrl,"Milind");
		RequestUrl request = new RequestUrl(mockLongUrl,Optional.empty());
		
		Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(Mono.empty());
		Mockito.when(urlRepository.save(Mockito.any(UrlEntity.class))).thenReturn(Mono.just(mockEntity));
		Mockito.when(urlReportRepository.save(Mockito.any(UrlReportEntity.class))).thenReturn(Mono.just(mockReportEntity));
		
		Mono<String> shortUrlResponse = service.generateShortUrl(request).doOnNext(System.out::println);
		
		StepVerifier
		.create(shortUrlResponse)
		.expectNext("http://localhost:8080/abc")
		.verifyComplete();
		
			
	}
	
	@Test
	public void InvalidUserTest() {
		String mockLongUrl = "http://google.com";
		RequestUrl request = new RequestUrl(mockLongUrl,Optional.of("Milind"));
		
		Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.empty());
		
		Mono<String> shortUrlResponse = service.generateShortUrl(request).doOnNext(System.out::println);
		
		StepVerifier
		.create(shortUrlResponse)
		.verifyError(InvalidUserIdException.class);
	}
	
	@Test
	public void UserExistsLongUrlNotExistsTest() {
		String mockLongUrl = "http://google.com";
		String mockShortUrl = "http://localhost:8080/abc";
		UrlEntity mockEntity = new UrlEntity("1",mockShortUrl,mockLongUrl,0L,LocalDate.now(), "Milind");
		UrlReportEntity mockReportEntity = new UrlReportEntity("1",LocalDate.now(),LocalDate.now(),0L,mockShortUrl,"Milind");
		UserEntity mockUserEntity = new UserEntity("1","Milind");
		RequestUrl request = new RequestUrl(mockLongUrl,Optional.of("Milind"));
		
		Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.just(mockUserEntity));
		Mockito.when(urlRepository.findByUserIdAndLongUrl(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(Mono.empty());
		Mockito.when(urlRepository.save(Mockito.any(UrlEntity.class))).thenReturn(Mono.just(mockEntity));
		Mockito.when(urlReportRepository.save(Mockito.any(UrlReportEntity.class))).thenReturn(Mono.just(mockReportEntity));
		
		Mono<String> shortUrlResponse = service.generateShortUrl(request).doOnNext(System.out::println);
		
		StepVerifier
		.create(shortUrlResponse)
		.expectNext("http://localhost:8080/abc")
		.verifyComplete();
	}
	
	@Test
	public void longUrlAlreadyExistsTest() {
		
		String mockLongUrl = "http://google.com";
		String mockShortUrl = "http://localhost:8080/abc";
		UrlEntity mockEntity = new UrlEntity("1",mockShortUrl,mockLongUrl,0L,LocalDate.now(),"Milind");
		UserEntity mockUserEntity = new UserEntity("1","Milind");
		Mockito.when(urlRepository.findByUserIdAndLongUrl(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(Mono.just(mockEntity));
		Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.just(mockUserEntity));
		RequestUrl request = new RequestUrl(mockLongUrl,Optional.of("Milind"));
		Mono<String> shortUrl = service.generateShortUrl(request).doOnNext(System.out::println);	
		
		StepVerifier
		.create(shortUrl)
		.verifyError(UrlAlreadyExistsException.class);
	}
	
	@Test
	public void shortUrlAsLongUrlTest() {
		String mockLongUrl = "http://localhost:8080/abc";
		RequestUrl request = new RequestUrl(mockLongUrl,Optional.empty());
		Mono<String> shortUrl = service.generateShortUrl(request).doOnNext(System.out::println);		
		
		StepVerifier
		.create(shortUrl)
		.verifyError(InvalidLongUrlException.class);
	}
	
	@Test
	public void InvalidLongUrlTest() {
		String mockLongUrl = "fdsfdsfsd";
		RequestUrl request = new RequestUrl(mockLongUrl,Optional.empty());
		Mono<String> shortUrl = service.generateShortUrl(request).doOnNext(System.out::println);		
		
		StepVerifier
		.create(shortUrl)
		.verifyError(InvalidLongUrlException.class);
	}
	
}
