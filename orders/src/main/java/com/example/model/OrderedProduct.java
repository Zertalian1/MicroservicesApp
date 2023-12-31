package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ordered_products")
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_product_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private BigDecimal price;
}
