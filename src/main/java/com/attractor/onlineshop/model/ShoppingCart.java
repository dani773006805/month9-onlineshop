package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private String userEmail;
    private BigDecimal totalPrice;
    private Integer totalQuantity;


}
