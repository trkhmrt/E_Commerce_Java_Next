package com.e_commerce.E_commerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favori extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long num;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;



}
