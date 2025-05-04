package org.example.reactivestockmanagement.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.reactivestockmanagement.model.StockMovement;
import org.example.reactivestockmanagement.repository.StockMovementRepository;
import org.example.reactivestockmanagement.service.RedisCacheService;
import org.example.reactivestockmanagement.service.StockMovementService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final RedisCacheService<StockMovement> redisCacheService;
    String key = "stockMovement";


    public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, RedisCacheService<StockMovement> redisCacheService) {
        this.stockMovementRepository = stockMovementRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public Flux<StockMovement> findAll() {
        return redisCacheService.get(key, new TypeReference<List<StockMovement>>() {})
                .collectList()  // Flux'u List'e topla
                .flatMapMany(cachedList -> {
                    // cachedList null veya boşsa, veritabanından alıyoruz
                    if (cachedList == null || cachedList.isEmpty()) {
                        return stockMovementRepository.findAll();
                    } else {
                        // cachedList varsa, onu kullanıyoruz
                        return Flux.fromIterable(cachedList);
                    }
                });
    }




    @Override
    public Mono save(StockMovement stockMovement) {
        return stockMovementRepository.save(stockMovement);

    }
}
