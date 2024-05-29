package com.example.springbootecommerce.dto;

import com.example.springbootecommerce.model.Product;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Builder
public record OrderDTO(
        Long id,
        Long userId,
        String email,
        List<Product> orderList,
        LocalDate orderDate,
        Double totalAmount
) {
}
