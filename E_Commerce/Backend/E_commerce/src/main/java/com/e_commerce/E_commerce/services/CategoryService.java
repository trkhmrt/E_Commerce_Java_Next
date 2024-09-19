package com.e_commerce.E_commerce.services;


import com.e_commerce.E_commerce.model.Category;
import com.e_commerce.E_commerce.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }



}
