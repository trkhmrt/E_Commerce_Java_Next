package com.e_commerce.E_commerce.controller;

import com.e_commerce.E_commerce.model.Basket;
import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.services.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/getbasketall")
    public ResponseEntity<List<Basket>> getAllBaskets() {
        List<Basket> baskets = basketService.getAllBaskets();




        return ResponseEntity.ok(baskets);
    }

    @GetMapping("/getuserbasket")
    public ResponseEntity<List<Product>> getUserBasket() {


        List<Product> user_basket = basketService.getUserBasket();


        return ResponseEntity.ok(user_basket);
    }


    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<String> addBasket(@PathVariable Long userId , @PathVariable Long productId) {

        basketService.addProductToBasket(userId,productId);
        return ResponseEntity.ok("Herşey tamam");
    }

}
