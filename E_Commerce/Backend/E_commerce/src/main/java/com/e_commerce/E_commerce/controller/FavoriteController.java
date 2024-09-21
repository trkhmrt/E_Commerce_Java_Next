package com.e_commerce.E_commerce.controller;

import com.e_commerce.E_commerce.services.FavoriService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
@AllArgsConstructor
public class FavoriteController {

    private final FavoriService favoriService;

    @GetMapping(path="/{productId}/{userId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long productId, @PathVariable Long userId) {
       if(favoriService.addFavori(productId,userId)){
           return ResponseEntity.ok("Successfully added favorite");
       }
       else{
           return ResponseEntity.badRequest().build();
       }
    }


}
