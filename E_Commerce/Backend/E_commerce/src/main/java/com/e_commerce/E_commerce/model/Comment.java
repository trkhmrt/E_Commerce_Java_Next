package com.e_commerce.E_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="product_id")
    private Product product;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="user_id")
    private User user;





}
