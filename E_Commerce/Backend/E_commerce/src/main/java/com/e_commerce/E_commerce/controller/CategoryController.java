package com.e_commerce.E_commerce.controller;


import com.e_commerce.E_commerce.model.Category;
import com.e_commerce.E_commerce.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(path="/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){

        categoryService.createCategory(category);

        return ResponseEntity.ok("Category created");

    }


}
