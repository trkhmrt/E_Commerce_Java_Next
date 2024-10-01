package com.e_commerce.E_commerce.services;


import com.e_commerce.E_commerce.auth.AuthenticationResponse;
import com.e_commerce.E_commerce.config.JwtService;
import com.e_commerce.E_commerce.model.Role;
import com.e_commerce.E_commerce.model.User;
import com.e_commerce.E_commerce.repositories.RoleRepository;
import com.e_commerce.E_commerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService   {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
 // Role repository

        public String save(UserRequest user) {


        User new_User = new User();
        new_User.setFirstName(user.getFirstName());
        new_User.setLastName(user.getLastName());
        new_User.setEmail(user.getEmail());
        new_User.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = roleRepository.findAllById(user.getRoleIds());
        for (Role role : roles) {
            System.out.println(role.name);
        }


        new_User.setRoles(roles);
            userRepository.save(new_User);

        var jwtToken = jwtService.generateToken(new_User);

        return jwtToken;
    }
}
