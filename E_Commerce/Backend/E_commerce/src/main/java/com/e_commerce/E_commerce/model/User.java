package com.e_commerce.E_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User  extends BaseEnttiy   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles", // Ortak tablo ismi
            joinColumns = @JoinColumn(name = "user_id"), // User id ile eşleştirilen sütun
            inverseJoinColumns = @JoinColumn(name = "role_id") // Role id ile eşleştirilen sütun
    )
    private Set<Role> roles = new HashSet<>();


    @OneToOne(mappedBy = "user")
    private Basket basket;
















}
