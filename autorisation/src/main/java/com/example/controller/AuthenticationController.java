package com.example.controller;

import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.RegisterRequest;
import com.example.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UsersService service;

    @PostMapping("/register")
    public HttpStatus register(
            @RequestBody RegisterRequest request
    ) {
        System.out.println("Test");
        service.register(request);
        return HttpStatus.OK;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+service.authenticate(request));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public HttpStatus validateToken(@RequestParam("token") String token) {
        if (service.validateUser(token)){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
