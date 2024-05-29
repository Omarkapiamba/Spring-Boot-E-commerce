package com.example.springbootecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 50,
            nullable = false)
    private String email;

    @ManyToMany
    @Column(nullable = false)
    private List<Product> orderList;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private Double totalAmount;

}
