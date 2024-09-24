package com.bermali.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bermali.domain.admin.AuthAdminDTO;
import com.bermali.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public String execute(AuthAdminDTO authAdminDTO) {
        var admin = this.adminRepository.findByEmail(authAdminDTO.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("Admin not found")
        );

        var passwordMatches = passwordEncoder.matches(authAdminDTO.getPassword(), admin.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Admin not found");
        }

        Algorithm algorithm = Algorithm.HMAC256(algorithmKey);

        var token = JWT.create().withIssuer("bermali")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(admin.getId().toString())
                .sign(algorithm);
        return token;
    }

}
