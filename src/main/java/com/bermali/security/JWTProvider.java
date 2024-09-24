package com.bermali.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bermali.domain.admin.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTProvider {

    @Value("${algorithm.key}")
    private String algorithmKey;

    public String generateToken(Admin admin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(algorithmKey);
            String token = JWT.create()
                    .withSubject(admin.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generation token", exception);
        }
    }

    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(algorithmKey);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}