package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.ShoppingCart;
import com.attractor.onlineshop.model.User;

import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> findByUserId(Long userId);

    Optional<ShoppingCart> findById(Long id);

    void deleteAllByCartId(Long userId);

    ShoppingCart create(User user);

    ShoppingCart updateStatus(Long cardId);
}
