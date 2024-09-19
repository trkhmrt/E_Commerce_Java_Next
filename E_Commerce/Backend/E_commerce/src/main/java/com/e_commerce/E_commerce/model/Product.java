package com.e_commerce.E_commerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product  extends BaseEnttiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;


    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;



    @ManyToMany(mappedBy = "products")
    private Set<Basket> baskets = new HashSet<>();
}
