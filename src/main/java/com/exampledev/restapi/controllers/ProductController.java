package com.example.restapi.controllers;

import com.example.restapi.dtos.ProductRecordDTO;
import com.example.restapi.models.ProductModel;
import com.example.restapi.services.ProductService;
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

    //Method will receive a recordDTO as the request body - @RequestBody needed.
    //@Valid is necessary, otherwise validations like @NotNull @NotBlank on DTO won't be taken into consideration.
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
        return productService.getOneProduct(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.updateProduct(id, productRecordDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        return productService.deleteProduct(id);
    }
}
