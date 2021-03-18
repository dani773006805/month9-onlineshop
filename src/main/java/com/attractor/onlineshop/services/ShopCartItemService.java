package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.ShopingCartItem;
import com.attractor.onlineshop.model.ShoppingCart;

import java.util.List;

public interface ShopCartItemService {
    void deleteByCartId(Long cartId);

    ShopingCartItem save(ShopingCartItem shopingCartItem);

    ShopingCartItem update(ShopingCartItem shopingCartItem);

    ShopingCartItem increment(Long productId, Long userId);

    ShopingCartItem decrement(Long productId, ShoppingCart shoppingCart);

    List<ShopingCartItem> findByCartId(Long id);

}
