package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("customers")
public class Customer {
    @Id
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
}
