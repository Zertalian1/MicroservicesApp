package com.example.dto;

import lombok.Builder;

@Builder
public record CustomerInfoDto(
        Long id,
        String firstName,
        String lastName
) {
}
