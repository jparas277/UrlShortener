package com.project.URLShortenerJava.Repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.URLShortenerJava.Bean.UrlReportEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UrlReportRepository extends ReactiveMongoRepository<UrlReportEntity, String>{
	Mono<UrlReportEntity> findByShortUrlAndFetchDate(String shortUrl, LocalDate date);
	Flux<UrlReportEntity> findByFetchDateAndClicksGreaterThan(LocalDate fetchDate,Integer clicks);
	Flux<UrlReportEntity> findByClicksGreaterThan(Integer clicks);
	Flux<UrlReportEntity> findByCreateDate(LocalDate createDate);
	
}
