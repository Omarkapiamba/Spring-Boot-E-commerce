package com.example.springbootecommerce.service.impl;

import com.example.springbootecommerce.dto.ProductDTO;
import com.example.springbootecommerce.dto.ProductRequest;
import com.example.springbootecommerce.dto.dtoMapper.ProductDTOMapper;
import com.example.springbootecommerce.exceptions.ResourceNotFoundException;
import com.example.springbootecommerce.repository.ProductRepository;
import com.example.springbootecommerce.model.Product;
import com.example.springbootecommerce.model.ProductCategory;
import com.example.springbootecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;

    }

    @Override
    public ProductDTO getProductById(Long id) {

        if (productRepository.existsById(id)) {
            return productRepository.findById(id)
                    .map(productDTOMapper)
                    .get();
        } else {
            throw new ResourceNotFoundException("Produkt", "id", id);
        }

    }

    @Override
    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(ProductCategory category) {


        return productRepository.findAllByCategory(category)
                .stream()
                .map(productDTOMapper)
                .toList();
    }

    @Override
    public List<ProductDTO> getAllProductsAvailable() {
        return productRepository.findAllByAvailable(true)
                .stream()
                .map(productDTOMapper)
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductRequest productRequest) {

        Product createdProduct = productRequestToProduct(productRequest);
        productRepository.save(createdProduct);

        return productDTOMapper.apply(createdProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductRequest productRequest) {

        Product updatedProduct = productRepository.findById(productRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Produkt", "id", productRequest.id()));

        updatedProduct.setModel(productRequest.model());
        updatedProduct.setMake(productRequest.make());
        updatedProduct.setPrice(productRequest.price());
        updatedProduct.setQuantityInStock(productRequest.quantityInStock());
        updatedProduct.setCategory(productRequest.category());
        updatedProduct.setAvailable(productRequest.available());
        updatedProduct.setRegistrationDate(productRequest.registrationDate());

        productRepository.save(updatedProduct);


        return productDTOMapper.apply(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.deleteById(id);
    }

    private Product productRequestToProduct(ProductRequest productRequest) {

        return new Product(
                productRequest.id(),
                productRequest.model(),
                productRequest.make(),
                productRequest.price(),
                productRequest.quantityInStock(),
                productRequest.category(),
                productRequest.available(),
                productRequest.registrationDate()
        );

    }
}
