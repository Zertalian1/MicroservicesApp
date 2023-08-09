package com.example.service;

import com.example.model.OrderedProduct;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record ProductServiceImpl(
        ProductRepository productRepository
) implements ProductService{
    @Override
    public List<OrderedProduct> createOrderedProductByProductId(List<Long> productIdList) {
        List<Product> productList = productRepository.getProductsByIdIn(productIdList);
        if (productIdList.size() != productList.size()) {
            throw new RuntimeException("Can not find products");
        }
        return productList.stream().map(
                product -> {
                    OrderedProduct orderedProduct = new OrderedProduct();
                    orderedProduct.setProduct(product);
                    orderedProduct.setPrice(product.getPrice());
                    return orderedProduct;
                }
        ).collect(Collectors.toCollection(ArrayList::new));
    }
}
