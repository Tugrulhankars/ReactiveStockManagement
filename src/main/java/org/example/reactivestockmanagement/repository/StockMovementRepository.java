package org.example.reactivestockmanagement.repository;

import org.example.reactivestockmanagement.model.StockMovement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockMovementRepository extends ReactiveCrudRepository<StockMovement, Long> {
}
