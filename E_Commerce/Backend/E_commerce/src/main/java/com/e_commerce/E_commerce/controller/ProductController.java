package com.e_commerce.E_commerce.controller;


import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.model.Views;
import com.e_commerce.E_commerce.services.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;

    @PostMapping(path="/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);

           // return  new ResponseEntity<>(createdProduct, HttpStatus.CREATED); veya
        return ResponseEntity.ok(createdProduct);
    }
}
