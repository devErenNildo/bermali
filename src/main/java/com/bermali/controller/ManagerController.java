package com.bermali.controller;

import com.bermali.domain.admin.Admin;
import com.bermali.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/confirm")
    public ResponseEntity<Admin> confirmAdmin(@RequestParam UUID id) {
        return ResponseEntity.ok(adminService.confirmAdmin(id));
    }

    @GetMapping("/getUsersPending")
    public ResponseEntity<List<Admin>> getUsersPending() {
        return ResponseEntity.ok(adminService.findAllAdminsPending());
    }
}
