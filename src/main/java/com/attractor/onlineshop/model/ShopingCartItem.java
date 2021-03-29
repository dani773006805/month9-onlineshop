package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_cart_item")
public class ShopingCartItem extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "units")
    private Integer units;

    @Column(name = "image_url")
    private String imageUrl;

    private Long categoryId;
    private Long productid;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart cart;

}
