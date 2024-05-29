package com.example.springbootecommerce.dto;

import com.example.springbootecommerce.model.ProductCategory;

import java.time.LocalDate;

public record ProductRequest(
        Long id,
        String model,
        String make,
        Double price,
        Integer quantityInStock,
        ProductCategory category,
        boolean available,
        LocalDate registrationDate
) {
}