package com.sservice.img.hosting.controller;

import com.sservice.img.hosting.dto.UserRegisterRequest;
import com.sservice.img.hosting.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/api/Auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRegisterRequest request) {
        return authService.loginUser(request);
    }
}
