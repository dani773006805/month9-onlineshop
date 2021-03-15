package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.ShoppingCartItemDto;
import com.attractor.onlineshop.model.Product;
import com.attractor.onlineshop.model.ShopingCartItem;
import com.attractor.onlineshop.model.ShoppingCart;

public class ShoppingCartItemMapper {
    public static ShopingCartItem fromProductToItem(Product product, ShoppingCart cart){
        return ShopingCartItem.builder()
                .productid(product.getId())
                .cart(cart)
                .categoryId(product.getCategory().getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .unitPrice(product.getUnitPrice())
                .units(1)
                .description(product.getDescription())
                .build();
    }
    public static ShoppingCartItemDto fromshopCartItemtoDto(ShopingCartItem shopingCartItem){
        return ShoppingCartItemDto.builder()
                .id(shopingCartItem.getId())
                .cartId(shopingCartItem.getCart().getId())
                .imageUrl(shopingCartItem.getImageUrl())
                .unitPrice(shopingCartItem.getUnitPrice())
                .units(shopingCartItem.getUnits())
                .name(shopingCartItem.getName())
                .description(shopingCartItem.getDescription())
                .build();
    }
}
