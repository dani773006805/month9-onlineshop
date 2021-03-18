package com.attractor.onlineshop.services;

import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.model.ShoppingCart;
import com.attractor.onlineshop.model.User;
import com.attractor.onlineshop.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;
    private ShopCartItemService shopCartItemService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Autowired
    public void setShopCartItemService(ShopCartItemServiceImpl shopCartItemService) {
        this.shopCartItemService = shopCartItemService;
    }

    public Optional<ShoppingCart> findByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    public void deleteAllByCartId(Long userId) {
        var cart = shoppingCartRepository.findByUserId(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Cart with userId %d doesn't exist", userId)));
        shopCartItemService.deleteByCartId(cart.getId());
        cart.setTotalPrice(new BigDecimal(0));
        cart.setTotalQuantity(0);
        shoppingCartRepository.save(cart);

    }

    public ShoppingCart create(User user) {
        var cart = ShoppingCart.builder()
                .user(user)
                .totalPrice(new BigDecimal(0))
                .totalQuantity(0)
                .build();
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart updateStatus(Long cardId) {
        var card = shoppingCartRepository.findById(cardId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Cart with id %d not found", cardId)));
        var items = shopCartItemService.findByCartId(cardId);
        BigDecimal totalPrice = new BigDecimal(0);
        Integer totalQuantity = 0;
        for (int i = 0; i < items.size(); i++) {
            totalQuantity = totalQuantity + items.get(i).getUnits();
            var totalPriceDouble = totalPrice.doubleValue();
            var price = items.get(i).getUnitPrice().doubleValue();
            totalPrice = new BigDecimal(totalPriceDouble + (price * items.get(i).getUnits()));
        }
        card.setTotalQuantity(totalQuantity);
        card.setTotalPrice(totalPrice);
        return shoppingCartRepository.save(card);

    }

}
