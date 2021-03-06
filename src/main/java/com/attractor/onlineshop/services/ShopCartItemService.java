package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.ShopingCartItem;
import com.attractor.onlineshop.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShopCartItemService {
    void deleteByCartId(Long cartId);

    ShopingCartItem save(ShopingCartItem shopingCartItem);

    ShopingCartItem update(ShopingCartItem shopingCartItem);

    ShopingCartItem increment(Long productId, String userEmail);

    ShopingCartItem decrement(Long productId, ShoppingCart shoppingCart);

    List<ShopingCartItem> findByCartId(Long id);

    void deleteAllByProductId(Long productId, ShoppingCart shoppingCart);
    Optional<ShopingCartItem> findByProductIdAndCardId(Long productId,Long shoppingCardId);

}
