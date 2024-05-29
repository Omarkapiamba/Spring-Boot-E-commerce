package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.model.Product;
import com.example.springbootecommerce.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory (ProductCategory category);

    List<Product> findAllByAvailable (Boolean available);



}
