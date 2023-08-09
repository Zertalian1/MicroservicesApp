package com.example.repository;

import com.example.dto.CustomerFilterDto;
import com.example.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryJdbcTemplates {
    private final NamedParameterJdbcOperations jdbcTemplate;

    public int addCustomer(Customer customer) {
        String query = """
                INSERT INTO customers(first_name, last_name, email) VALUES (:firstName, :lastName, :email) RETURNING id
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

    public List<Long> getAllCustomersIdsByFilter(CustomerFilterDto filter) {
        String query = """
                SELECT customers.id AS id
                FROM customers
                WHERE
                    customers.first_name||' '||customers.last_name ILIKE '%' || :name || '%' OR
                    customers.last_name||' '||customers.first_name ILIKE '%' || :name || '%'
                """.trim();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", filter.firstName() + filter.lastName());
        return jdbcTemplate.queryForList(query, parameters, Long.class);
    }
}
