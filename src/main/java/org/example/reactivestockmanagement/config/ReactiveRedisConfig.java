package org.example.reactivestockmanagement.config;

import org.example.reactivestockmanagement.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class ReactiveRedisConfig {
    private String host="localhost";
    private int port=6379;

    @Bean
    public ReactiveRedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host,port);
    }

    @Bean
    public ReactiveRedisTemplate<String, User> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory){
        return new ReactiveRedisTemplate<String,User>(
                reactiveRedisConnectionFactory,
                RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(User.class))
        );
    }

   /* @Bean
    public ReactiveRedisMessageListenerContainer redisMessageListenerContainer(){

    }*/
}
