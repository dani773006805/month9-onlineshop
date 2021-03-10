package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_cart")
public class ShoppingCart extends BaseEntity {
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "product_shopCart",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> carts = new ArrayList<>();

}
