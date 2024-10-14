package com.bermali.controller;

import com.bermali.domain.admin.Admin;
import com.bermali.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> confirmAdmin(@RequestParam UUID id) {
        return ResponseEntity.ok(adminService.confirmAdmin(id));
    }
}
