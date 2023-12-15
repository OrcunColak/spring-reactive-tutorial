package com.colak.springreactivetutorial.redis.config;

import com.colak.springreactivetutorial.redis.model.RedisUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class ReactiveRedisTemplateConfig {

    @Bean
    public ReactiveRedisTemplate<String, RedisUser> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(RedisUser.class);
        return new ReactiveRedisTemplate<String, RedisUser>(
                factory,
                RedisSerializationContext.fromSerializer(serializer)
        );
    }
}
