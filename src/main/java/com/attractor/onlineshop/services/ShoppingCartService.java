package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> findByUserEmail(String email);

    Optional<ShoppingCart> findById(Long id);

    void deleteAllByUserEmail(String email);

    ShoppingCart create(String email);

    ShoppingCart updateStatus(ShoppingCart shoppingCart);
}
