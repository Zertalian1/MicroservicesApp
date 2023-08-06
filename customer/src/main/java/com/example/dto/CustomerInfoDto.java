package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CustomerInfoDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
}
