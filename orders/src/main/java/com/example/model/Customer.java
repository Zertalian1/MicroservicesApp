package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
