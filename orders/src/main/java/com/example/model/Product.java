package com.example.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
}
