package org.example.reactivestockmanagement.service.impl;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.example.reactivestockmanagement.model.MovementType;
import org.example.reactivestockmanagement.model.Product;
import org.example.reactivestockmanagement.model.StockMovement;
import org.example.reactivestockmanagement.repository.ProductRepository;
import org.example.reactivestockmanagement.service.ProductService;
import org.example.reactivestockmanagement.service.RedisCacheService;
import org.example.reactivestockmanagement.service.StockMovementService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StockMovementService stockMovementService;
    private final RedisCacheService redisCacheService;
    private final static Logger log=Logger.getLogger(ProductServiceImpl.class.getName());
    private final Counter counter;

    public ProductServiceImpl(MeterRegistry registry, ProductRepository productRepository, StockMovementService stockMovementService, RedisCacheService redisCacheService) {
        this.productRepository = productRepository;
        this.stockMovementService = stockMovementService;
        this.redisCacheService = redisCacheService;
        this.counter = Counter.builder("product-service")
                .description("Product save counter")
                .tag("method", "save")
                .register(registry);
    }

    @Override
   // @NewSpan(value = "product-service-save-span")
    @Counted(value = "product-service-save",description = "Product save counter")
    public Mono<String> save(Product product) {
        return productRepository.save(product)
                .flatMap(p->{
                     var key=String.valueOf(p.getId());
                     //counter.increment();
                     return redisCacheService.set(key,p, Duration.ofMinutes(1));
                })
                .doOnSubscribe(s -> System.out.println("SUBSCRIBED on: " + Thread.currentThread().getName()))
               // .doOnSuccess(p -> counter.increment())
                .thenReturn("Product saved successfully")
                .onErrorResume(ex -> {
                    System.out.println("ERROR on: " + Thread.currentThread().getName());
                    return Mono.just("Product not saved");
                });

        /*Tracer tracer = GlobalOpenTelemetry.getTracer("my-app");

        Span span = tracer.spanBuilder("product-service-save-span").startSpan();
        try (Scope scope = span.makeCurrent()) {
            return productRepository.save(product)
                    .doOnSuccess(p -> span.end())
                    .onErrorResume(ex -> {
                        span.recordException(ex);
                        span.end();
                        return Mono.just("Product not saved");
                    });
        }*/

    }

    @Override
    //@NewSpan(value = "product-service-delete-span")
    public Mono<String> delete(Long id) {
        return productRepository.findById(id)
                .flatMap(product -> productRepository.delete(product)
                        .thenReturn("Product deleted successfully"))
                .switchIfEmpty(Mono.just("Product not found"))
                .onErrorReturn("Product not deleted");
    }


    @Override
    public Mono<String> update(Product product) {
        return null;
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(Long id) {
        return redisCacheService.get("product:"+id,Product.class)
                .switchIfEmpty(productRepository.findById(id)
                        .switchIfEmpty(Mono.error(new RuntimeException("Product not found"))));
        /*return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));*/

    }

    @Override
    public Mono<String> increaseStockAmount(Long productId, int quantity) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .flatMap(product -> {
                    // Yeni stok hareketi oluştur
                    StockMovement stockMovement = new StockMovement();
                    stockMovement.setProductId(product.getId());
                    stockMovement.setQuantity(quantity);
                    stockMovement.setMovementType(MovementType.IN);
                    stockMovement.setMovementTime(LocalDateTime.now());

                    // Ürün stok miktarını güncelle
                    product.setStockQuantity(product.getStockQuantity() + quantity);
                    redisCacheService.delete("stokmovement");
                    redisCacheService.delete("product:"+product.getId());

                    // Önce stok hareketini kaydet, ardından ürünü güncelle
                    return stockMovementService.save(stockMovement)
                            .then(productRepository.save(product))
                            .thenReturn("Stock updated successfully");
                });
    }



    @Override
    public Mono<String> decreaseStockAmount(Long productId, int quantity) {

        return productRepository.findById(productId)
                .flatMap(product -> {//flatMap findById'den dönen Mono<Product>'yi işlemek için kullanılır. Eğer ürün bulunursa, ürünün stoğunu günceller ve ardından kaydederiz. Eğer ürün bulunmazsa, hata fırlatılır.
                    if (product!=null){
                        StockMovement stockMovement=new StockMovement();
                        stockMovement.setProductId(product.getId());
                        stockMovement.setQuantity(quantity);
                        stockMovement.setMovementType(MovementType.OUT);
                        stockMovement.setMovementTime(LocalDateTime.now());
                       // stockMovementService.save(stockMovement).subscribe();
                        product.setStockQuantity(product.getStockQuantity()-quantity);

                        return stockMovementService.save(stockMovement)
                                .then(productRepository.save(product))
                                .thenReturn("Stock updated successfully");
                        /*return productRepository.save(product)
                                .thenReturn("Stock updated successfully");*/
                    }else {
                        return Mono.error(new RuntimeException("Product not found"));
                    }
                });
    }
}
