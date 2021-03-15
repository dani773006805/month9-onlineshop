package com.attractor.onlineshop.services;

import com.attractor.onlineshop.dto.mapper.ShoppingCartItemMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.model.ShopingCartItem;
import com.attractor.onlineshop.model.ShoppingCart;
import com.attractor.onlineshop.repositories.ShopCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCartItemService {
    private ShopCartItemRepository shopCartItemRepository;
    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    public ShopCartItemService(ShopCartItemRepository shopCartItemRepository) {
        this.shopCartItemRepository = shopCartItemRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public void deleteByCartId(Long cartId) {
        shopCartItemRepository.deleteAllByCartId(cartId);
    }

    public ShopingCartItem save(ShopingCartItem shopingCartItem) {
        return shopCartItemRepository.save(shopingCartItem);
    }

    public ShopingCartItem update(ShopingCartItem shopingCartItem) {
        return shopCartItemRepository.save(shopingCartItem);
    }

    public ShopingCartItem increment(Long productId, Long userId) {
        var cart = shoppingCartService.findByUserId(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Cart with id %d not found", userId)));
        var shoppCartItem = new ShopingCartItem();
        var item = shopCartItemRepository.findByCartIdAndProductid(cart.getId(), productId);
        if (item.isPresent()) {
            var existItem = item.get();
            existItem.setUnits(existItem.getUnits() + 1);
            shoppCartItem = update(existItem);
        } else {
            var product = productService.findByIdAndActive(productId).orElseThrow(() ->
                    new ResourceNotFoundException(String.format("Product with id %d not found", productId)));
            var shopItem = ShoppingCartItemMapper.fromProductToItem(product, cart);
            shoppCartItem = save(shopItem);
        }
        shoppingCartService.updateStatus(cart.getId());
        return shoppCartItem;
    }

    public ShopingCartItem decrement(Long productId, ShoppingCart shoppingCart) {
        var shopCartItem = shopCartItemRepository.findByCartIdAndProductid(shoppingCart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No cartItem with id %d not found", shoppingCart.getId())));
        shopCartItem.setUnits(shopCartItem.getUnits() - 1);
        if (shopCartItem.getUnits() == 0) {
            shopCartItemRepository.deleteById(shopCartItem.getId());
            shoppingCartService.updateStatus(shopCartItem.getCart().getId());
            return null;
        } else {
            var cartItem = update(shopCartItem);
            shoppingCartService.updateStatus(shopCartItem.getCart().getId());
            return cartItem;
        }
    }

    public List<ShopingCartItem> findByCartId(Long id) {
        return shopCartItemRepository.findByCartId(id);
    }
}
