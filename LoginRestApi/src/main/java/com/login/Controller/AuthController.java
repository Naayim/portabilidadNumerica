package com.login.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.common.LogConfig;
import com.login.dto.AuthResponse;
import com.login.dto.LoginRequest;
import com.login.dto.RegisterRequest;
import com.login.service.AuthService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
	LogConfig.initLog4j2();
	
	return ResponseEntity.ok(authService.login(loginRequest));
    }
    
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
	LogConfig.initLog4j2();
	
	return ResponseEntity.ok(authService.register(registerRequest));
    }
    
}
