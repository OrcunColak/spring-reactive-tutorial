package com.colak.springreactivetutorial.redis.config;

import com.colak.springreactivetutorial.redis.model.RedisUser;
import com.colak.springreactivetutorial.redis.repository.RedisUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Configuration
public class ReactiveRedisMessageListenerContainerConfig {

    @Bean
    public ReactiveRedisMessageListenerContainer redisMessageListenerContainer(RedisUserRepository userRepository,
                                                                               ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(reactiveRedisConnectionFactory);
        ObjectMapper objectMapper = new ObjectMapper();
        container.receive(ChannelTopic.of(RedisUserRepository.LIST_NAME))
                .map(ReactiveSubscription.Message::getMessage)
                .map(m -> {
                    try {
                        return objectMapper.readValue(m, RedisUser.class);
                    } catch (IOException e) {
                        return null;
                    }
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException()))
                .flatMap(userRepository::saveUserNoTTL)
                .subscribe(c -> log.info("User saved."));
        return container;
    }
}
