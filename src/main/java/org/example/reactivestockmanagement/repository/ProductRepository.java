package org.example.reactivestockmanagement.repository;

import org.example.reactivestockmanagement.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {
}
