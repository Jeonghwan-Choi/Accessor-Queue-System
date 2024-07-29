package com.queue.flow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

//    @Bean
//    public ReactiveRedisTemplate<String, Long> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
//        RedisSerializationContext.RedisSerializationContextBuilder<String, Long> builder =
//                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
//
//        RedisSerializationContext<String, Long> context = builder
//                .value((RedisSerializationContext.SerializationPair<Long>) new GenericJackson2JsonRedisSerializer())
//                .build();
//
//        return new ReactiveRedisTemplate<>(factory, context);
//    }
}
