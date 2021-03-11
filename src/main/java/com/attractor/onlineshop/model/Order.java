package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


    @Column(name = "date_created")
    private LocalDateTime dateCreated;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "product_order",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products=new ArrayList<>();





}
