package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.ProductDTO;
import com.example.springbootecommerce.dto.ProductRequest;
import com.example.springbootecommerce.exceptions.ResourceNotFoundException;
import com.example.springbootecommerce.model.ProductCategory;
import com.example.springbootecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @GetMapping(path = "/{productId}")
    public ProductDTO getProductById (@PathVariable("productId") Long id) {

        return productService.getProductById(id);
    }

    @GetMapping()
    public List<ProductDTO> getAllProducts () {

        return productService.getAllProducts();
    }

    @GetMapping(path = "/category/{category}")
    public List<ProductDTO> getAllProductsByCategory (@PathVariable("category") ProductCategory category) {

        return productService.getAllProductsByCategory(category);
    }

    @GetMapping(path = "/available")
    public List<ProductDTO> getAllProductsAvailable () {
        return productService.getAllProductsAvailable();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ProductDTO> createProduct (@RequestBody ProductRequest productRequest) {

        ProductDTO productDTO = productService.createProduct(productRequest);

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }


    @PutMapping(path = "/update")
    public ResponseEntity<ProductDTO> updateProduct (@RequestBody ProductRequest productRequest) {

        ProductDTO productDTO = productService.updateProduct(productRequest);

        return new ResponseEntity<>(productDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping (path = "/delete/{productId}")
    public ResponseEntity<String> deleteProduct (@PathVariable("productId") Long id) {
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>(String.format("Produkt med id: %s är raderad", id), HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(String.format("Produkt med id: %s hittades inte", id), HttpStatus.NOT_FOUND);

        }

    }

}
