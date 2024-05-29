package com.example.springbootecommerce.dto.dtoMapper;

import com.example.springbootecommerce.dto.ProductDTO;
import com.example.springbootecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getModel(),
                product.getMake(),
                product.getPrice(),
                product.getQuantityInStock(),
                product.getCategory(),
                product.isAvailable(),
                product.getRegistrationDate()
        );
    }

}
