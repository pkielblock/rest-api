package com.kielblockdev.restapi.controllers;

import com.kielblockdev.restapi.dtos.ProductRecordDTO;
import com.kielblockdev.restapi.models.ProductModel;
import com.kielblockdev.restapi.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.saveProduct(productRecordDTO);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(name = "id") UUID id) {
        return  productService.getOneProduct(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.updateProduct(id, productRecordDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        return productService.deleteProduct(id);
    }
}
