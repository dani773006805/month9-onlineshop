package com.attractor.onlineshop.services;

import com.attractor.onlineshop.dto.mapper.ShoppingCartItemMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.model.ShopingCartItem;
import com.attractor.onlineshop.model.ShoppingCart;
import com.attractor.onlineshop.repositories.ShopCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopCartItemServiceImpl implements ShopCartItemService {
    private ShopCartItemRepository shopCartItemRepository;
    private ProductService productService;
    private ShoppingCartServiceImpl shoppingCartService;


    @Autowired
    public void setShopCartItemRepository(ShopCartItemRepository shopCartItemRepository) {
        this.shopCartItemRepository = shopCartItemRepository;
    }

    @Autowired
    public void setProductService(ProductService productServiceImpl) {
        this.productService = productServiceImpl;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartServiceImpl shoppingCartService) {
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

    public ShopingCartItem increment(Long productId, String email) {
        var cart = shoppingCartService.findByUserEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("cart is not found"));

        var shoppCartItem = new ShopingCartItem();
        var item = shopCartItemRepository
                .findByCartIdAndProductid(cart.getId(), productId);
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
        shoppingCartService.updateStatus(cart);
        return shoppCartItem;
    }

    public ShopingCartItem decrement(Long productId, ShoppingCart shoppingCart) {
        var shopCartItem = shopCartItemRepository.findByCartIdAndProductid(shoppingCart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No cartItem with id %d not found", shoppingCart.getId())));
        if (shopCartItem.getUnits() < 2) {
            var id = shopCartItem.getId();
            shopCartItemRepository.deleteById(id);
            shopCartItemRepository.delete(shopCartItem);
            var cardItemcheck = shopCartItemRepository.findById(id);
            shoppingCartService.updateStatus(shoppingCart);
            return null;
        } else {
            shopCartItem.setUnits(shopCartItem.getUnits() - 1);
            var cartItem = update(shopCartItem);
            shoppingCartService.updateStatus(shoppingCart);
            return cartItem;
        }
    }


    @Override
    public Optional<ShopingCartItem> findByProductIdAndCardId(Long productId, Long shoppingCardId) {
        return shopCartItemRepository.findByProductidAndCartId(productId, shoppingCardId);
    }

    @Transactional
    @Override
    public void deleteAllByProductId(Long productId, ShoppingCart shoppingCart) {
        shopCartItemRepository.deleteByProductidAndCartId(productId, shoppingCart.getId());
        shoppingCartService.updateStatus(shoppingCart);
    }

    public List<ShopingCartItem> findByCartId(Long id) {

        var items = shopCartItemRepository
                .findByCartId(id).stream().sorted(Comparator.comparing(ShopingCartItem::getProductid))
                .collect(Collectors.toList());
        return items;
    }
}
