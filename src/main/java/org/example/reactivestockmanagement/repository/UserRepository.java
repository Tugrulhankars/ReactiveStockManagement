package org.example.reactivestockmanagement.repository;

import org.example.reactivestockmanagement.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Long> {
    Mono<User> findByEmail(String username);
    Flux<User> findAllBy(Pageable pageable);
}
