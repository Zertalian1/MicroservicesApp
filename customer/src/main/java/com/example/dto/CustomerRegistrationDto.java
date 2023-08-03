package com.example.dto;

import lombok.Builder;

@Builder
public record CustomerRegistrationDto(
        String firstName,
        String lastName,
        String email
) {
}
