package com.e_commerce.E_commerce.repositories;

import com.e_commerce.E_commerce.model.User;
import com.e_commerce.E_commerce.services.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
