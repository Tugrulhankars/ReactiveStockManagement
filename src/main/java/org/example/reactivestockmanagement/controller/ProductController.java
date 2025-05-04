package org.example.reactivestockmanagement.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.example.reactivestockmanagement.model.Product;
import org.example.reactivestockmanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*@Timed(value = "product-service-save", histogram = true)
    @Counted(value = "product-service-save",description = "Product save counter")*/
    @PostMapping("/save")
    public Mono<String> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/incrementStock")
    public Mono<String> incrementStock(@RequestParam Long productId, @RequestParam int quantity) {
        return productService.increaseStockAmount(productId, quantity);
    }

    @GetMapping("/findAll")
    public Flux<Product> findAll() {
        return productService.findAll();
    }
}
