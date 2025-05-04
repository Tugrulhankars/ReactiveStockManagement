package org.example.reactivestockmanagement.service.impl;

/*import org.example.reactivestockmanagement.dto.request.LoginRequest;
import org.example.reactivestockmanagement.dto.request.RegisterRequest;
import org.example.reactivestockmanagement.model.Role;
import org.example.reactivestockmanagement.model.User;
import org.example.reactivestockmanagement.repository.UserRepository;
import org.example.reactivestockmanagement.service.AuthenticationService;
import org.example.reactivestockmanagement.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(ReactiveAuthenticationManager reactiveAuthenticationManager, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Map<String, String>> login(LoginRequest request) {
        return reactiveAuthenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()))
                .then(getUserDetails(request.username()))
                .map(this::createAuthResponse);
    }

    private Mono<User> getUserDetails(String username){
        return userRepository.findByEmail(username);
    }

    @Override
    public Mono<String> register(Mono<RegisterRequest> request) {
        return request
                .flatMap(this::convertToUser)
                .flatMap(userRepository::save)
                .doOnSuccess(user -> System.out.println("User saved successfully"))
                .thenReturn("User saved successfully")
                .onErrorResume(ex->{
                    ex.printStackTrace();
                    return Mono.just("User not saved"+ex.getMessage());
                });
    }


    private Map<String,String>createAuthResponse(User user){
        Map<String,String> result=new HashMap<>();
        result.put("userId",user.getId().toString());
        result.put("token",jwtService.generateJwt(user.getId().toString())); // Replace it with actual JWT
        return result;
    }

    public Mono<User> convertToUser(RegisterRequest request){
        return Mono.fromCallable(()->{
           User user=new User();
            BeanUtils.copyProperties(request,user);
            user.setPassword(passwordEncoder.encode(request.password()));
            user.setRole(Role.USER);
            return user;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}*/
