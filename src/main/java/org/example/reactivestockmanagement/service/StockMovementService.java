package org.example.reactivestockmanagement.service;

import org.example.reactivestockmanagement.model.StockMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockMovementService {

    Flux<StockMovement> findAll();
    Mono save(StockMovement stockMovement);

}
