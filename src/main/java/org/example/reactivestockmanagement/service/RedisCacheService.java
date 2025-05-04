package org.example.reactivestockmanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public interface RedisCacheService<T> {

     <T> Flux<T> get(String key, TypeReference<List<T>> typeReference);
    <T> Mono<T> get(String key, Class<T> clazz);
    Mono<Boolean> set(String key, T data, Duration ttl);
    Mono<Boolean> delete(String key);

}
