package com.example.service;

import com.example.config.JwtService;
import com.example.dto.AuthenticationRequest;
import com.example.dto.RegisterRequest;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record UsersService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager
) {

    public void register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRole())
                .build();
        userRepository.save(user);
    }

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByUsername(request.getUserName()).orElseThrow();
        return jwtService.generateToken(user);
    }

    public boolean validateUser(String token) {
        final String userName = jwtService.extractUsername(token);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userRepository.findUserByUsername(userName).orElse(null);
            return userDetails != null && jwtService.isTokenValid(token, userDetails);
        }
        return false;
    }
}
