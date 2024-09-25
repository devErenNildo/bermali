package com.bermali.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bermali.domain.admin.Admin;
import com.bermali.domain.admin.AuthAdminDTO;
import com.bermali.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;


@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${algorithm.key}")
    private String algorithmKey;

    public String execute(AuthAdminDTO authAdminDTO) throws AuthenticationException {
        var admin = adminRepository.findByEmail(authAdminDTO.getEmail()).orElseThrow(
            ()-> {
                throw new UsernameNotFoundException("User not found");
            }
        );

        var passwordMatches = passwordEncoder.matches(authAdminDTO.getPassword(), admin.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Password does not match");
        }

        Algorithm algorithm = Algorithm.HMAC256(algorithmKey);
        var token = JWT.create()
                .withIssuer("bermali")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(4)))
                .withSubject(admin.getId().toString())
                .sign(algorithm);
        return token;
    }
}
