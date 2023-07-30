package com.example.repository;

import com.example.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final NamedParameterJdbcOperations jdbcTemplate;

    public int addProduct(Product product) {
        String query = """
                INSERT INTO products(name, description, price) VALUES (:name, :description, :price) RETURNING id
                """.trim();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("description", product.getDescription())
                .addValue("price", product.getPrice());
        Integer id = jdbcTemplate.queryForObject(query, parameters, Integer.class);
        if (id == null) {
            throw new RuntimeException("Can not save new product");
        }
        return id;
    }
}
