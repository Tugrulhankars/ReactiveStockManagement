package org.example.reactivestockmanagement.controller;

import org.example.reactivestockmanagement.dto.request.LoginRequest;
import org.example.reactivestockmanagement.dto.request.RegisterRequest;
import org.example.reactivestockmanagement.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/*@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody Mono<LoginRequest> loginRequest){
        return loginRequest
                .flatMap(loginReq->
                        authenticationService.login(loginReq))
                .map(authenticationResultMap->ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION,"Bearer"
                        + authenticationResultMap.get("token"))
                        .header("UserId",authenticationResultMap.get("userId"))
                        .build());


    }

    @PostMapping("/register")
    public Mono<ResponseEntity<String>> register(@RequestBody Mono<RegisterRequest> registerRequest){
        return authenticationService.register(registerRequest)
                .flatMap(result->Mono.just(ResponseEntity.ok(result)));

    }
}*/
