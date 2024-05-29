package com.example.springbootecommerce.dto;

import com.example.springbootecommerce.model.Product;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record OrderRequest(
        Long id,
        Long userId,
        String email,
        List<Product> orderList,
        LocalDate orderDate,
        Double totalAmount
) {

}
