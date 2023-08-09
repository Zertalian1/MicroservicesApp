package com.example.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
}
