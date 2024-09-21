package com.e_commerce.E_commerce.controller;


import com.e_commerce.E_commerce.dto.ProductDto;
import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.services.ProductService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);

           // return  new ResponseEntity<>(createdProduct, HttpStatus.CREATED); veya
        return ResponseEntity.ok(createdProduct);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);

    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product founded_product = productService.getProductById(id);

        if (founded_product == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(founded_product);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {


        if (product==null) {
            return ResponseEntity.notFound().build();
        }
        else{
           // productService.createProduct(product); gelen nesne tekrardan kaydedilirse var olan veri güncellenir.
          //  Product updated_product = entityManager.merge(product); //buda güncellemenin farklı bir yolu enittymanager ile

            return ResponseEntity.ok("");
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        if (productService.getProductById(id) == null) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product Deleted");
        }
        return ResponseEntity.notFound().build();
    }




}
