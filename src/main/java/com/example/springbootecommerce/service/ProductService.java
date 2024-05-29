package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.ProductDTO;
import com.example.springbootecommerce.dto.ProductRequest;
import com.example.springbootecommerce.model.ProductCategory;

import java.util.List;

public interface ProductService {

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductsByCategory(ProductCategory category);

    List<ProductDTO> getAllProductsAvailable();

    ProductDTO createProduct(ProductRequest productRequest);

    ProductDTO updateProduct(ProductRequest productRequest);

    void deleteProduct(Long id);

}
