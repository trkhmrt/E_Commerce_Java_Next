package com.e_commerce.E_commerce.services;

import com.e_commerce.E_commerce.model.Favori;
import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.model.User;
import com.e_commerce.E_commerce.repositories.FavoriRepository;
import com.e_commerce.E_commerce.repositories.ProductRepository;
import com.e_commerce.E_commerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriService {

    private final FavoriRepository favoriRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public boolean addFavori(Long productId, Long userId) {

        Optional<Product> product = productRepository.findById(productId);
        Optional<User> user = userRepository.findById(userId);
        if (product.isPresent() && user.isPresent()) {
            Favori favori = new Favori();
            favori.setProduct(product.get());
            favori.setUser(user.get());
            favoriRepository.save(favori);
            return true;
        }

        return false;
    }
}
