package org.example.reactivestockmanagement.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface JwtService {

    String generateJwt(String subject);
    Mono<Boolean> validateJwt(String jwt);
    String extractTokenSubject(String jwt);
}
