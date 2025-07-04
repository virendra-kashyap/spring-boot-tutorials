package com.azure.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

     @GetMapping("/")
    public String publicEndpoint() {
        return "Welcome to the public endpoint! <a href='/profile'>Login</a>";
    }

    @GetMapping("/profile")
    public Map<String, Object> userInfo(Principal principal) {
        OidcUser user = (OidcUser) principal;
        return Map.of(
                "name", user.getFullName(),
                "email", user.getEmail(),
                "claims", user.getClaims()
        );
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }
}