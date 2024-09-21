package com.bermali.services;

import com.bermali.domain.admin.AuthAdminDTO;
import com.bermali.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthAdmin {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthAdminDTO authAdminDTO) {
        var admin = this.adminRepository.findByEmail(authAdminDTO.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("Admin not found")
        );

        var passwordMatches = passwordEncoder.matches(authAdminDTO.getPassword(), admin.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Admin not found");
        }
    }

}
