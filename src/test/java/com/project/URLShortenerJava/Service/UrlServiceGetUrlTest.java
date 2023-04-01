package com.project.URLShortenerJava.Service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.URLShortenerJava.Bean.UrlEntity;
import com.project.URLShortenerJava.Bean.UrlReportEntity;
import com.project.URLShortenerJava.Exception.ShortUrlNotFoundException;
import com.project.URLShortenerJava.Repository.UrlReportRepository;
import com.project.URLShortenerJava.Repository.UrlRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UrlServiceGetUrlTest {
	
	private static String domain = "http://localhost:8080/";
	@Mock
	private UrlRepository urlRepository;
	
	@Mock
	private UrlReportRepository urlReportRepository;
	
	@InjectMocks
	private UrlService service;
	
	@Test
	public void longUrlFoundTest() {
		
		String mockLongUrl = "http://google.com";
		String mockShortUrl = "abc";
		UrlEntity mockEntity = new UrlEntity("1",domain + mockShortUrl,mockLongUrl,0L,LocalDate.now(), "Milind");
		UrlReportEntity mockReportEntity = new UrlReportEntity("1",LocalDate.now(),LocalDate.now(),0L,domain + mockShortUrl,"Milind");
		Mockito.when(urlRepository.findByShortUrl(Mockito.any(String.class))).thenReturn(Mono.just(mockEntity));
		Mockito.when(urlRepository.save(Mockito.any(UrlEntity.class))).thenReturn(Mono.just(mockEntity));
		Mockito.when(urlReportRepository.findByShortUrlAndFetchDate(Mockito.any(String.class),Mockito.any(LocalDate.class))).thenReturn(Mono.just(mockReportEntity));
		Mockito.when(urlReportRepository.save(Mockito.any(UrlReportEntity.class))).thenReturn(Mono.just(mockReportEntity));
		Mono<String> longUrl = service.getLongUrl(mockShortUrl);
		
		StepVerifier.create(longUrl)
		.expectNextMatches(url -> url.equals(mockLongUrl))
		.verifyComplete();
	}
	
	@Test
	public void longUrlFoundAndClicksExpiredTest() {
		String mockLongUrl = "http://google.com";
		String mockShortUrl = "abc";
		UrlEntity mockEntity = new UrlEntity("1",domain + mockShortUrl,mockLongUrl,9L,LocalDate.now(), "Milind");
		UrlReportEntity mockReportEntity = new UrlReportEntity("1",LocalDate.now(),LocalDate.now(),9L,domain + mockShortUrl,"Milind");
		Mockito.when(urlRepository.findByShortUrl(Mockito.any(String.class))).thenReturn(Mono.just(mockEntity));
		Mockito.when(urlRepository.delete(Mockito.any(UrlEntity.class))).thenReturn(Mono.empty());
		Mockito.when(urlReportRepository.findByShortUrlAndFetchDate(Mockito.any(String.class),Mockito.any(LocalDate.class))).thenReturn(Mono.just(mockReportEntity));
		Mockito.when(urlReportRepository.save(Mockito.any(UrlReportEntity.class))).thenReturn(Mono.just(mockReportEntity));
		Mono<String> longUrl = service.getLongUrl(mockShortUrl);
		
		StepVerifier.create(longUrl)
		.expectNextMatches(url -> url.equals(mockLongUrl))
		.verifyComplete();
	}
	
	@Test
	public void longUrlNotFoundTest() {
		String mockShortUrl = "abc";
		Mockito.when(urlRepository.findByShortUrl(Mockito.any(String.class))).thenReturn(Mono.empty());
		Mono<String> longUrl = service.getLongUrl(mockShortUrl);
		
		StepVerifier.create(longUrl)
		.verifyError(ShortUrlNotFoundException.class);
	}
	
	
}
