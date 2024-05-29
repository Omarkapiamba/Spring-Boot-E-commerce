package com.example.springbootecommerce.dto;

import com.example.springbootecommerce.model.UserRole;

import java.time.LocalDate;

public record UserRequest(
        Long id,
        String username,
        String password,
        String name,
        String lastName,
        Long personalNumber,
        String phoneNumber,
        String adress,
        String email,
        LocalDate registrationDate,
        UserRole userRole,
        Boolean active
) {
}