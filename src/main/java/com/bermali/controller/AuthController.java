package com.bermali.controller;

import com.bermali.domain.admin.AdminRequestDTO;
import com.bermali.domain.admin.AuthAdminDTO;
import com.bermali.services.AdminService;
import com.bermali.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthAdminDTO authAdminDTO) {
        try{
            var result= this.authService.execute(authAdminDTO);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> register (@RequestBody AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(adminService.createAdmin(adminRequestDTO));
    }
}
