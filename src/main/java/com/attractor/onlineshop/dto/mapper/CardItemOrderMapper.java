package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.model.Order;
import com.attractor.onlineshop.model.OrderItem;
import com.attractor.onlineshop.model.ShopingCartItem;
import com.attractor.onlineshop.model.ShoppingCart;

public class CardItemOrderMapper {
    public static OrderItem shopItemToOrderItem(ShopingCartItem shopingCartItem){
        return OrderItem.builder()
                .categoryId(shopingCartItem.getCategoryId())
                .productId(shopingCartItem.getProductid())
                .imageUrl(shopingCartItem.getImageUrl())
                .description(shopingCartItem.getDescription())
                .name(shopingCartItem.getName())
                .unitPrice(shopingCartItem.getUnitPrice())
                .unitsI(shopingCartItem.getUnits())
                .build();
    }
    public static Order shopCartToOrder(ShoppingCart shoppingCart){
        return Order.builder()
                .totalPrice(shoppingCart.getTotalPrice())
                .totalQuantity(shoppingCart.getTotalQuantity())
                .build();
    }
}
