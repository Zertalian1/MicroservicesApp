package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    private Long id;
    private LocalDateTime orderDate;
    private LocalDateTime completedDate;
    private String comment;
    private Long customerId;
    @ManyToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "orders_ordered_products",
            joinColumns = {@JoinColumn(name = "orders_id")},
            inverseJoinColumns = {@JoinColumn(name = "ordered_products_id")}
    )
    private List<OrderedProduct> productsList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
    }
}
