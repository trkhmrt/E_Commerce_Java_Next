package com.e_commerce.E_commerce.services;


import com.e_commerce.E_commerce.model.Basket;
import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.model.User;
import com.e_commerce.E_commerce.repositories.BasketRepository;
import com.e_commerce.E_commerce.repositories.ProductRepository;
import com.e_commerce.E_commerce.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@AllArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void addProductToBasket(Long userId,Long productId) {
        // Kullanıcıyı bul
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Sepeti bul
        Basket basket = user.getBasket();

        // Ürünü bul
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // Eğer ürün zaten sepette yoksa ekle
        List<Product> products = basket.getProducts();

        if (basket.getProducts().contains(product)) {
            System.out.println("Product already exists");
        }
        else{
            basket.getProducts().add(product);

            // Toplam fiyatı güncelle
            basket.setTotalprice(basket.getTotalprice() + product.getPrice());

            // Sepeti kaydet
            userRepository.save(user);
        }

    }


}
