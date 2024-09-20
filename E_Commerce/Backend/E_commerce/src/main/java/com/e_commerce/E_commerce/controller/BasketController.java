package com.e_commerce.E_commerce.controller;

import com.e_commerce.E_commerce.dto.UserBasketDTO;
import com.e_commerce.E_commerce.model.Basket;
import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.services.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<String> addBasket(@PathVariable Long userId , @PathVariable Long productId) {

        basketService.addProductToBasket(userId,productId);
        return ResponseEntity.ok("Herşey tamam");
    }

}
