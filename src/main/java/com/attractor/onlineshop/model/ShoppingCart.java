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
public class ShoppingCart extends BaseEntity{
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<Product> products=new ArrayList<>();
}
