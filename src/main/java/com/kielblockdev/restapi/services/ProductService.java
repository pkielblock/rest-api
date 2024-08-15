package com.kielblockdev.restapi.services;

import com.kielblockdev.restapi.dtos.ProductRecordDTO;
import com.kielblockdev.restapi.models.ProductModel;
import com.kielblockdev.restapi.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<ProductModel> saveProduct(ProductRecordDTO productRecordDTO) {
        //var allows us to not declare the instance type on the left side, the right side is enough
        var productModel = new ProductModel();
        /*
         * DTO is necessary only to receive the data and validate it. Once that is done we should
         * move it to a model object, which will be used to send it to the database.
         * */
        BeanUtils.copyProperties(productRecordDTO, productModel); //Converting from DTO to Model
        //Saving to DB using the repository methods. And the model is used on the parameter.
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    //ResponseEntity should be of type List<Model> as we will receive all data.
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    //ResponseEntity type Object as we will have two returns with differente types.
    public ResponseEntity<Object> getOneProduct(UUID id) {
        //Being a model needs to be optional as we may not find the product.
        Optional<ProductModel> productO = productRepository.findById(id); //Tries to find it by ID using JPA repository
        if(productO.isEmpty()) {
            //Returns 404 + message if not found
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        //Method .get() from our optional model to get the product if it exists.
        return  ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    public ResponseEntity<Object> updateProduct(UUID id, ProductRecordDTO productRecordDTO) {
        //Checks if the product exists than saves to DB.
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = productO.get(); //Get old object if found.
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    public ResponseEntity<Object> deleteProduct(UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        //Delete product before return
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
