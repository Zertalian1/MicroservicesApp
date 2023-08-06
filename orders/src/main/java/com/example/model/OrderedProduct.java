package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ordered_products")
public class OrderedProduct {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private BigDecimal price;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ordered_products")
    private List<Order> orders = new ArrayList<>();

}
