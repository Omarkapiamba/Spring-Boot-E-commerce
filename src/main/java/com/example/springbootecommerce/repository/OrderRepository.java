package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByEmail(String email);

    List<Order> findAllByOrderDate(LocalDate date);


}
