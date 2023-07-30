package com.example.repository;

import com.example.model.Customer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final NamedParameterJdbcOperations jdbcTemplate;

    public int addCustomer(Customer customer) {
        String query = """
                INSERT INTO customers(firstName, lastName, email) VALUES (:firstName, :lastName, :email) RETURNING id
                """.trim();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", customer.getFirstName())
                .addValue("lastName", customer.getLastName())
                .addValue("email", customer.getEmail());
        Integer id = jdbcTemplate.queryForObject(query, parameters, Integer.class);
        if (id == null) {
            throw new RuntimeException("Can not save new user");
        }
        return id;
    }
}
