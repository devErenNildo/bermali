package com.bermali.services;

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
	
	public Admin createAdmin(Admin admin) {

		var password = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(password);
		return adminRepository.save(admin);
	}
	
}
