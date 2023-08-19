package com.example.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMINISTRATOR,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
