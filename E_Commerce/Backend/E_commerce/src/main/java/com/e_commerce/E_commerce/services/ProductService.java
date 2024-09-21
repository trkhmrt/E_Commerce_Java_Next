package com.e_commerce.E_commerce.services;

import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.repositories.ProductRepository;
import com.e_commerce.E_commerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService   {

    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        Optional<Product> founded_product=productRepository.findById(id);

        if(founded_product.isPresent()){
            return founded_product.get();
        }
        else{
            return null;
        }

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
