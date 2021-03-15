package com.attractor.onlineshop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private List<ShoppingCartItemDto> itemDtos = new ArrayList<>();
}
