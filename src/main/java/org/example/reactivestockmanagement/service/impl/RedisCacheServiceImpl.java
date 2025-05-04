package org.example.reactivestockmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.reactivestockmanagement.service.RedisCacheService;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class RedisCacheServiceImpl<T> implements RedisCacheService<T> {
    private final ReactiveRedisTemplate<String ,String> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisCacheServiceImpl(ReactiveRedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> Flux<T> get(String key, TypeReference<List<T>> typeReference) {
        return redisTemplate.opsForValue()
                .get(key)
                .flatMapMany(json -> {
                    try {
                        List<T> list = objectMapper.readValue(json, typeReference);
                        return Flux.fromIterable(list);
                    } catch (JsonProcessingException e) {
                        return Flux.error(e);
                    }
                });
    }

    public <T> Mono<T> get(String key, Class<T> clazz) {
        return redisTemplate.opsForValue()
                .get(key)
                .flatMap(json -> {
                    try {
                        T obj = objectMapper.readValue(json, clazz);
                        return Mono.just(obj);
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }


    @Override
    public Mono<Boolean> set(String key, T data, Duration ttl) {
        try {
            String json=objectMapper.writeValueAsString(data);
            return redisTemplate.opsForValue().set(key,json,ttl);
        }catch (JsonProcessingException e){
            return Mono.error(e);
        }
    }

    @Override
    public Mono<Boolean> delete(String key) {
        return redisTemplate.delete(key).map(deleted->deleted>0);
    }
}
