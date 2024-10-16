package com.bermali.services;

import com.bermali.domain.admin.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bermali.domain.admin.Admin;
import com.bermali.repositories.AdminRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

	public Admin confirmAdmin(UUID id) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("User not found"));

		admin.setRole(Role.ROLE_ADMIN);

		return adminRepository.save(admin);
	}

	public List<Admin> findAllAdminsPending() {
		List<Admin> admins = adminRepository.findAll().stream()
				.filter(user -> user.getRole() == Role.ROLE_USER)
				.toList();

		return admins;
	}

	public List<Admin> findAllAdminsAccepted() {
		List<Admin> admins = adminRepository.findAll().stream()
				.filter(user -> user.getRole() == Role.ROLE_ADMIN)
				.toList();

		return admins;
	}
}
