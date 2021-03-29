package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.mapper.ShoppingCartItemMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.services.ShopCartItemService;
import com.attractor.onlineshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/cart-details")
    public ResponseEntity<?> getUsersCartDetail(Principal principal){
        if(principal.getName()==null){
            throw new ResourceNotFoundException("illegal user name");
        }
        var cartOptional=shoppingCartService.findByUserEmail(principal.getName());
        if (!cartOptional.isPresent()) {
            shoppingCartService.create(principal.getName());
        }
        var cart=shoppingCartService.findByUserEmail(principal.getName()).get();
        var cartDto=ShoppingCartItemMapper.fromCardToDto(cart);
        return ResponseEntity.ok(cartDto);

    }

    @GetMapping
    public ResponseEntity<?> getUsersCart(Principal principal) {
        if(principal.getName()==null){
            throw new ResourceNotFoundException("illegal user name");
        }
        var cartOptional = shoppingCartService.findByUserEmail(principal.getName());
        if (!cartOptional.isPresent()) {
            shoppingCartService.create(principal.getName());
        }
        var cart=shoppingCartService.findByUserEmail(principal.getName()).get();
        var cartItemDtos = shopCartItemService.findByCartId(cart.getId()).stream()
                .map(ShoppingCartItemMapper::fromshopCartItemtoDto).collect(Collectors.toList());
        return ResponseEntity.ok(cartItemDtos);
    }

    @PostMapping("/increment/{productId}")
    public ResponseEntity<?> increment(@PathVariable Long productId,Principal principal) {
        if(principal.getName()==null){
            throw new ResourceNotFoundException("illegal username");
        }
        var cartItemDto = ShoppingCartItemMapper
                .fromshopCartItemtoDto(shopCartItemService.increment(productId, principal.getName()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartItemDto);
    }

    @PostMapping("/remove/{itemId}")
    public ResponseEntity<?> remove(@PathVariable Long itemId,Principal user){
        if (user == null) {
            throw new ResourceNotFoundException("user email is null");
        }
        var shoppingCart = shoppingCartService.findByUserEmail(user.getName())
                .orElseThrow(() -> new UserNotFoundException(String.format("cart with id %s not found", user.getName())));
        shopCartItemService.deleteAllByProductId(itemId,shoppingCart);
        var check=shopCartItemService.findByProductIdAndCardId(itemId,shoppingCart.getId());
        if(check.isPresent()){
            return ResponseEntity.badRequest().body(check.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/decrement/{itemId}")
    public ResponseEntity<?> decrement(@PathVariable Long itemId, Principal user) {
        if (user == null) {
            throw new ResourceNotFoundException("user email is null");
        }
        var shoppingCart = shoppingCartService.findByUserEmail(user.getName())
                .orElseThrow(() -> new UserNotFoundException(String.format("cart with id %s not found", user.getName())));
        var item = shopCartItemService.decrement(itemId, shoppingCart);
        if (item == null) {
            return ResponseEntity.ok().body("no more");
        } else {
            var itemDto=ShoppingCartItemMapper.fromshopCartItemtoDto(item);
            return ResponseEntity.ok(itemDto);
        }
    }
}
