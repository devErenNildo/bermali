package com.bermali.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bermali.domain.admin.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTProvider {

    @Value("${algorithm.key}")
    private String algorithmKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(algorithmKey);

        try {
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String generateToken(Admin admin) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(algorithmKey);
            return JWT.create()
                    .withIssuer("bermali")
                    .withExpiresAt(generateTokenExpirationTime())
                    .withSubject(admin.getId().toString())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Error while generating token", e);
        }
    }

    private Instant generateTokenExpirationTime() {
        return Instant.now().plus(Duration.ofHours(4));
    }
}