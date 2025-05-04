package org.example.reactivestockmanagement.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.reactivestockmanagement.service.JwtService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

/*@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateJwt(String subject) {
        return Jwts
                .builder()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public Mono<Boolean> validateJwt(String jwt) {
        return Mono.just(jwt)
                .map(token->parseToken(jwt))
                .map(claims -> !claims.getExpiration().before(new Date()))
                .onErrorReturn(false);
    }

    @Override
    public String extractTokenSubject(String jwt) {
        return parseToken(jwt).getSubject();
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSigningKey() {
        return Optional.ofNullable("")
                .map(tokenSecret->tokenSecret.getBytes())
                .map(tokenSecretBytes-> Keys.hmacShaKeyFor(tokenSecretBytes))
                .orElseThrow(()->new IllegalArgumentException("token.secret must be configured in the application.properties file"));
    }


}*/
