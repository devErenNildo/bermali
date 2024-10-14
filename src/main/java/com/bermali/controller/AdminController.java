package com.bermali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermali.domain.admin.Admin;
import com.bermali.domain.admin.AdminRequestDTO;
import com.bermali.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> createAdmin(@RequestBody AdminRequestDTO adminRequestDTO){
		
		Admin admin = new Admin(
				adminRequestDTO.getName(),
				adminRequestDTO.getEmail(),
				adminRequestDTO.getPassword()
		);
		
		return ResponseEntity.ok(adminService.createAdmin(admin));
	}
	
}
