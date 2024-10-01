package com.e_commerce.E_commerce.controller;

import com.e_commerce.E_commerce.config.JwtService;
import com.e_commerce.E_commerce.model.User;
import com.e_commerce.E_commerce.repositories.UserRepository;
import com.e_commerce.E_commerce.services.UserRequest;
import com.e_commerce.E_commerce.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String createUser(@RequestBody UserRequest user) {


        return  userService.save(user);
    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginRequest data) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword())
        );
        var user = userRepository.findByEmail(data.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return jwtToken;

    }

}
