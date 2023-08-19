package com.example.controller;

import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.RegisterRequest;
import com.example.model.UserRole;
import com.example.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UsersService service;

    @PostMapping("/register")
    public HttpStatus register(
            @RequestBody RegisterRequest request
    ) throws IOException {
        System.out.println("Test");
        service.register(request);
        return HttpStatus.OK;
    }

    @PostMapping("/register-2")
    public HttpStatus registerSec(
    ) throws IOException {
        System.out.println("Test");
        RegisterRequest request = RegisterRequest.builder()
                .userName("test")
                .password("password")
                .role(Set.of(UserRole.ROLE_ADMIN))
                .build();
        service.register(request);
        return HttpStatus.OK;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/test")
    public String test(){
        return "Test";
    }

    @GetMapping("/secure-test")
    public String secureTest(){
        return "secureTest";
    }

}
