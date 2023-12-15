package com.colak.springreactivetutorial.redis.repository;

import com.colak.springreactivetutorial.redis.model.RedisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class RedisUserRepository {

    public static final String LIST_NAME = "users";

    private final ReactiveRedisOperations<String, RedisUser> reactiveRedisOperations;

    public Flux<RedisUser> findAllUsers() {
        return this.reactiveRedisOperations.opsForList().range(LIST_NAME, 0, -1);
    }

    public Mono<RedisUser> findUserById(String id) {
        return this.findAllUsers().filter(p -> p.getId().equals(id))
                .switchIfEmpty(Mono.just(RedisUser.builder().build()))
                .last();
    }

    public Mono<String> saveUserWithTTL(RedisUser user) {
        user.setId(UUID.randomUUID().toString());

        // Set TTL to 60 seconds
        Duration ttl = Duration.ofSeconds(60);

        return reactiveRedisOperations.opsForList()
                .rightPush(LIST_NAME, user)
                .flatMap(result -> reactiveRedisOperations.expire(LIST_NAME, ttl))
                .map(result -> "List set with TTL");

    }

    public Mono<Long> saveUserNoTTL(RedisUser user) {
        user.setId(UUID.randomUUID().toString());
        return reactiveRedisOperations.opsForList()
                .rightPush(LIST_NAME, user);
    }

    public Mono<Boolean> deleteAllUsers() {
        return this.reactiveRedisOperations.opsForList().delete(LIST_NAME);
    }

    public Mono<Long> publishUser(RedisUser user) {
        user.setId(UUID.randomUUID().toString());
        return this.reactiveRedisOperations.convertAndSend(LIST_NAME, user);
    }
}
