package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsServiceImpl userDetailsService;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
			AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	public LoginResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
		return new LoginResponse(token, user.getUsername(), user.getRole());
	}

	public String register(RegisterRequest request) {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exists");
		}
		String role = (request.getRole() != null && !request.getRole().isBlank()) ? request.getRole() : "ROLE_USER";
		User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), role);
		userRepository.save(user);
		return "User registered successfully";
	}
}
