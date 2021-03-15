package com.attractor.onlineshop.dto;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderItemDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer units;
    private String imageUrl;
    private Long categoryId;


}