package com.project.URLShortenerJava.Service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UrlControllerGetReport {
//	private static String domain = "http://localhost:8080/";
//	
//	@Mock
//	private UrlReportRepository urlReportRepository;
//	
//	@InjectMocks
//	private UrlService service;
	
	
//	@Test
//	public void getReportByDateTest() {
//		
//		String date = "2022-03-23";
//		String mockUrl1 = "http://localhost:8080/abc";
//		String mockUrl2 = "http://localhost:8080/abc";
//		
//		Flux<UrlReportEntity> mockFlux = Flux.just(
//				new UrlReportEntity("1", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl1),
//				new UrlReportEntity("2", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl2));
//		
//		Mockito.when(urlReportRepository.findByFetchDate(Mockito.any(LocalDate.class))).thenReturn(mockFlux);
//		Flux<UrlReportDto> report = service.getReportByDate(date);
//		
//		StepVerifier.create(report)
//		.expectNextCount(2)
//		.verifyComplete();
//	}
//	
//	@Test
//	public void getReportByInvalidDateTest() {
//		
//		String date = "20-03-23";
//		
//		Flux<UrlReportDto> report = service.getReportByDate(date)
//				.doOnError(err -> System.out.println(err.getMessage()));
//		
//		StepVerifier.create(report)
//		.verifyError(InvalidDateException.class);
//	}
//	
//	@Test
//	public void getReportWithoutDateTest() {
//		
//		String date = "2022-03-23";
//		String mockUrl1 = "http://localhost:8080/abc";
//		String mockUrl2 = "http://localhost:8080/abc";
//		
//		Flux<UrlReportEntity> mockFlux = Flux.just(
//				new UrlReportEntity("1", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl1),
//				new UrlReportEntity("2", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl2));
//		
//		Mockito.when(urlReportRepository.findAll()).thenReturn(mockFlux);
//		Flux<UrlReportDto> report = service.getReportAll();
//		
//		StepVerifier.create(report)
//		.expectNextCount(2)
//		.verifyComplete();
//	}
//	
//	
//	@Test
//	public void getGeneratedUrlByDateTest() {
//		
//		String date = "2022-03-23";
//		String mockUrl1 = "http://localhost:8080/abc";
//		String mockUrl2 = "http://localhost:8080/abc";
//		
//		Flux<UrlReportEntity> mockFlux = Flux.just(
//				new UrlReportEntity("1", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl1),
//				new UrlReportEntity("2", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl2));
//		
//		Mockito.when(urlReportRepository.findByCreateDate(Mockito.any(LocalDate.class))).thenReturn(mockFlux);
//		Flux<UrlReportDto> report = service.getGeneratedUrlReportByDate(date);
//		
//		StepVerifier.create(report)
//		.expectNextCount(2)
//		.verifyComplete();
//	}
//	
//	@Test
//	public void getGeneratedUrlWithoutDateTest() {
//		
//		String date = "2022-03-23";
//		String mockUrl1 = "http://localhost:8080/abc";
//		String mockUrl2 = "http://localhost:8080/abc";
//		
//		Flux<UrlReportEntity> mockFlux = Flux.just(
//				new UrlReportEntity("1", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl1),
//				new UrlReportEntity("2", LocalDate.parse(date), LocalDate.parse(date), 0L, mockUrl2));
//		
//		Mockito.when(urlReportRepository.findAll()).thenReturn(mockFlux);
//		Flux<UrlReportDto> report = service.getGeneratedUrlReportAll();
//		
//		StepVerifier.create(report)
//		.expectNextCount(2)
//		.verifyComplete();
//	}
//	
//	@Test
//	public void getGeneratedReportByInvalidDateTest() {
//		
//		String date = "20-03-23";
//		
//		Flux<UrlReportDto> report = service.getGeneratedUrlReportByDate(date)
//				.doOnError(err -> System.out.println(err.getMessage()));
//		
//		StepVerifier.create(report)
//		.verifyError(InvalidDateException.class);
//	}
	
}
