package org.example.reactivestockmanagement.controller;

import org.example.reactivestockmanagement.model.StockMovement;
import org.example.reactivestockmanagement.service.StockMovementService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stock-movement")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping("/save")
    public Mono<String> save(@RequestBody StockMovement stockMovement) {
        return stockMovementService.save(stockMovement);
    }

    @GetMapping("/all")
    public Flux<StockMovement> findAll() {
        return stockMovementService.findAll();
    }
}
