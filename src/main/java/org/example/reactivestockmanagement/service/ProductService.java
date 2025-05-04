package org.example.reactivestockmanagement.service;

import org.example.reactivestockmanagement.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<String> save(Product product);
    Mono<String> delete(Long id);
    Mono<String> update(Product product);
    Flux<Product> findAll();
    Mono<Product> findById(Long id);
    Mono<String>  increaseStockAmount(Long productId, int quantity);
    Mono<String>  decreaseStockAmount(Long productId, int quantity);
}
