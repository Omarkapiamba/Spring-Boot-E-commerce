package com.example.springbootecommerce.dto;

import com.example.springbootecommerce.model.UserRole;

import java.time.LocalDate;

public record UserDTO(
        Long id,
        String username,
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
