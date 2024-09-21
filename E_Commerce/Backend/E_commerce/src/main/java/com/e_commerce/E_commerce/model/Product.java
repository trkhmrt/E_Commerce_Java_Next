package com.e_commerce.E_commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product  extends BaseEntity {
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



    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Basket> baskets = new ArrayList<>();


    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', description='" + description + "', price=" + price + "}";
    }

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Favori> favoritedByUsers;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Comment> comments;



}
