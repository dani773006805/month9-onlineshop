package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.ShoppingCartDto;
import com.attractor.onlineshop.dto.mapper.ShoppingCartItemMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.services.ShopCartItemService;
import com.attractor.onlineshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
public class ShopCartController {
    private ShoppingCartService shoppingCartService;
    private ShopCartItemService shopCartItemService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setShopCartItemService(ShopCartItemService shopCartItemService) {
        this.shopCartItemService = shopCartItemService;
    }

    @GetMapping
    public ResponseEntity<?> getUsersCart(Long userId) {
        var cart = shoppingCartService.findByUserId(2L).orElseThrow(() ->
                new ResourceNotFoundException(String.format("cart with id %d not found", userId)));
        var cartItemDtos=shopCartItemService.findByCartId(cart.getId()).stream()
                .map(ShoppingCartItemMapper::fromshopCartItemtoDto).collect(Collectors.toList());
        var cartDto= ShoppingCartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .itemDtos(cartItemDtos)
                .build();
        return ResponseEntity.ok(cartDto);
    }
    @PostMapping("/increment")
    public ResponseEntity<?> increment(@RequestBody Long productId){
        Long userId=0L;
        var cartItemDto=ShoppingCartItemMapper
                .fromshopCartItemtoDto(shopCartItemService.increment(productId,userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @PostMapping ("/decrement")
    public ResponseEntity<?> decrement(@RequestBody Long itemId){
        var userId=1L;
        var shoppingCart=shoppingCartService.findByUserId(userId)
                .orElseThrow(()->new UserNotFoundException(String.format("User with id %d not found",userId)));
        var item=shopCartItemService.decrement(itemId,shoppingCart);
        if(item==null){
            return ResponseEntity.ok().body("no more");
        }else {
            return ResponseEntity.ok(item);
        }
    }
}
