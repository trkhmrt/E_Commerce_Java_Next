package com.e_commerce.E_commerce.services;


import com.e_commerce.E_commerce.model.Basket;
import com.e_commerce.E_commerce.model.Product;
import com.e_commerce.E_commerce.model.User;
import com.e_commerce.E_commerce.repositories.BasketRepository;
import com.e_commerce.E_commerce.repositories.ProductRepository;
import com.e_commerce.E_commerce.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public List<Product> getUserBasket(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();
        Optional<Basket> user_basket = basketRepository.findByUserId(userId);
        List<Product> user_basket_products = user_basket.get().getProducts();

        return user_basket_products;
    }


    public void addProductToBasket(Long userId,Long productId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));


        Basket basket = user.getBasket();


        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));


        if (basket.getProducts().contains(product)) {
            System.out.println("Product already exists");
        }
        else{
            basket.getProducts().add(product);


            basket.setTotalprice(basket.getTotalprice() + product.getPrice());


            userRepository.save(user);
        }

    }


}
