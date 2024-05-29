package com.example.springbootecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantityInStock;

    @Column(nullable = false)
    private ProductCategory category;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false,
            updatable = false)
    private LocalDate registrationDate;


}
