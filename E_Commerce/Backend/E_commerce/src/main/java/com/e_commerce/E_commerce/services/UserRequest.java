package com.e_commerce.E_commerce.services;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Long> roleIds;

    // Getters and Setters
}