package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CustomerRegistrationDto {
    private final String firstName;
    private final String lastName;
    private final String email;
}
