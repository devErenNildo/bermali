package com.bermali.services;

import com.bermali.domain.admin.AdminRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bermali.domain.admin.Admin;
import com.bermali.repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String createAdmin(AdminRequestDTO adminRequestDTO) {
		Admin admin = new Admin(
				adminRequestDTO.getName(),
				adminRequestDTO.getEmail(),
				passwordEncoder.encode(adminRequestDTO.getPassword())
		);

		return "order sent, wait for administrator confirmation";
	}
	
}
