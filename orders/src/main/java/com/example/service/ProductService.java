package com.example.service;

import com.example.model.OrderedProduct;

import java.util.List;

public interface ProductService {
    List<OrderedProduct> createOrderedProductByProductId(List<Long> productIdList);
}
