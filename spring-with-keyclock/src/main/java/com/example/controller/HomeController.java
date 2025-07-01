package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('role_user')")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello From User!");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('role_admin')")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello From Admin!");
    }
    
}