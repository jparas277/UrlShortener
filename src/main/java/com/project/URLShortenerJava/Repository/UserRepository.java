package com.project.URLShortenerJava.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.URLShortenerJava.Bean.UserEntity;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
	Mono<UserEntity> findByUserId(String userId);
}
