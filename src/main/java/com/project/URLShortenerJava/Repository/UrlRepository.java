package com.project.URLShortenerJava.Repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.URLShortenerJava.Bean.UrlEntity;

import reactor.core.publisher.Mono;

public interface UrlRepository extends ReactiveMongoRepository<UrlEntity, String>{
	Mono<UrlEntity> findByLongUrl(String longUrl);
	Mono<UrlEntity> findByShortUrl(String shortUrl);
	Mono<UrlEntity> findByUserIdAndLongUrl(String userId, String longUrl);
}
