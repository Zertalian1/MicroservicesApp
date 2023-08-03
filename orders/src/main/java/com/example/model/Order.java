package com.example.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class Order {
    @Id
    private final Long id;
    private final LocalDateTime orderDate;
    private final LocalDateTime completedDate;
    private final String comment;
    private final Long CustomerId;
    //@MappedCollection(idColumn = "products_id")
    //private final Set<Product> products;
}
