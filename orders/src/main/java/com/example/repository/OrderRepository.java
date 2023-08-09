package com.example.repository;

import com.example.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, CrudRepository<Order, Long> {

    Page<Order> getOrdersByCustomerIdIn(List<Long> customersIdList, Pageable pageable);


}
