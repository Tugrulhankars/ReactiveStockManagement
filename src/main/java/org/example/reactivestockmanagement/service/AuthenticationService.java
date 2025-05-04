package org.example.reactivestockmanagement.service;

import org.example.reactivestockmanagement.dto.request.LoginRequest;
import org.example.reactivestockmanagement.dto.request.RegisterRequest;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthenticationService {
    Mono<Map<String,String>> login(LoginRequest request);
    Mono<String> register(Mono<RegisterRequest> request);
}
