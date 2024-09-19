package com.e_commerce.E_commerce.repositories;

import com.e_commerce.E_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
