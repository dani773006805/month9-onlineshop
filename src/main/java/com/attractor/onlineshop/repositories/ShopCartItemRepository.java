package com.attractor.onlineshop.repositories;

import com.attractor.onlineshop.model.ShopingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopCartItemRepository extends JpaRepository<ShopingCartItem,Long> {
    void deleteAllByCartId(Long id);
    void deleteById(Long id);
    ShopingCartItem save(ShopingCartItem shopingCartItem);
    List<ShopingCartItem> findByCartId(Long id);
    Optional<ShopingCartItem> findByCartIdAndProductid(Long cartId,Long productId);
}
