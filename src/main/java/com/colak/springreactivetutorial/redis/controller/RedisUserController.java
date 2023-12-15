package com.colak.springreactivetutorial.redis.controller;

import com.colak.springreactivetutorial.redis.model.RedisUser;
import com.colak.springreactivetutorial.redis.repository.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/redisusers")
public class RedisUserController {

    private final RedisUserRepository userRepository;

    @PostMapping(path = "")
    public Mono<Long> saveUser(@RequestBody RedisUser user) {
        return userRepository.saveUserNoTTL(user);
    }

    @GetMapping(path = "/{id}")
    public Mono<RedisUser> findUserById(@PathVariable String id) {
        return userRepository.findUserById(id);
    }

    @GetMapping(path = "")
    public Flux<RedisUser> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @PostMapping(path = "/publish")
    public Mono<Long> publishUser(@RequestBody RedisUser user) {
        return userRepository.publishUser(user);
    }

    @DeleteMapping(path = "")
    public Mono<Boolean> deleteAllUsers() {
        return userRepository.deleteAllUsers();
    }
}
