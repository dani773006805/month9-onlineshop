package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Table(name = "product_category")
@Entity
public class ProductCategory extends BaseEntity {

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products =new HashSet<>();

}