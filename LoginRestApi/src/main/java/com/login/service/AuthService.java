package com.login.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.common.LogConfig;
import com.login.dto.AuthResponse;
import com.login.dto.LoginRequest;
import com.login.dto.RegisterRequest;
import com.login.jwt.JwtService;
import com.login.model.Role;
import com.login.model.User;
import com.login.reposirory.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
	LogConfig.initLog4j2();
	
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
	String token = jwtService.getToken(user);
	
	return AuthResponse.builder()
		.token(token)
		.build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
	LogConfig.initLog4j2();
	
	 User user = User.builder()
	            .username(registerRequest.getUsername())
	            .password(passwordEncoder.encode(registerRequest.getPassword()))
	            .firstname(registerRequest.getFirstname())
	            .lastname(registerRequest.getLastname())
	            .role(Role.USER)
	            .build();

	        userRepository.save(user);

	        return AuthResponse.builder()
	            .token(jwtService.getToken(user))
	            .build();
    }

}
