package org.example.reactivestockmanagement.service.impl;

import org.example.reactivestockmanagement.dto.request.CreateUserRequest;
import org.example.reactivestockmanagement.dto.response.UserResponse;
import org.example.reactivestockmanagement.model.User;
import org.example.reactivestockmanagement.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class UserServiceImpl  {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   /* @Override
    public Mono<UserResponse> createUser(Mono<CreateUserRequest> request) {
        return request
                .map(this::convertToUser) // map çünkü dönüş tipi User
                .flatMap(userRepository::save)
                .map(this::convertToUserResponse); // istersen UserResponse'a çevir
    }*/


    /*@Override
    public Mono<UserResponse> getUserById(Long id, String jwt) {
        return userRepository
                .findById(id)
                .mapNotNull(user->convertToUserResponse(user))
                .flatMap(userEntity->{
                    return Mono.just(userEntity);
                });
    }

    @Override
    public Flux<UserResponse> findAll(int page, int limit) {
        if (page>0)page=page-1;
        Pageable pageable= PageRequest.of(page,limit);
        return userRepository.findAllBy(pageable)
                .map(user->convertToUserResponse(user));
    }*/

    /*@Override
    public Mono<String> findUserByEmail(String email) {
        /*return userRepository.findByEmail(email)
                .map(userEntity->
                        );*/
        //return null;
    }

   /* @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(userEntity-> org.springframework.security.core.userdetails.User
                        .withUsername(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(new ArrayList<>())
                        .build());
    }*/

   /* private Mono<User> convertToUser(CreateUserRequest request) {
       return Mono.fromCallable(()->{
          User user=new User();
          BeanUtils.copyProperties(request,user);
          user.setPassword();
       });
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return Mono.just(user);
    }*/

    /*private UserResponse convertToUserResponse(User user) {
        UserResponse userResponse=new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        return userResponse;
    }
}*/
