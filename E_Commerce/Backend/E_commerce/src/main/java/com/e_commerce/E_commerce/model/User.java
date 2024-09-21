package com.e_commerce.E_commerce.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles", // Ortak tablo ismi
            joinColumns = @JoinColumn(name = "user_id"), // User id ile eşleştirilen sütun
            inverseJoinColumns = @JoinColumn(name = "role_id") // Role id ile eşleştirilen sütun
    )
    private List<Role> roles = new ArrayList<>();


    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    private Basket basket;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Favori> favorites;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Comment> comments;


}
