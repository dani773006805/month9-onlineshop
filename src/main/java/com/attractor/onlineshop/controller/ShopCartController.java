package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.ShoppingCartDto;
import com.attractor.onlineshop.dto.mapper.ShoppingCartItemMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.services.ShopCartItemService;
import com.attractor.onlineshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "Access-Control-Allow-Origin")
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
        var cartDto = ShoppingCartDto.builder()
                .id(cart.getId())
                .userEmail(cart.getUserEmail())
                .itemDtos(cartItemDtos)
                .build();
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/increment/{productId}")
    public ResponseEntity<?> increment(@PathVariable Long productId,Principal principal) {
        if(principal.getName()==null){
            throw new ResourceNotFoundException("illegal username");
        }
        var cartItemDto = ShoppingCartItemMapper
                .fromshopCartItemtoDto(shopCartItemService.increment(productId, principal.getName()));
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Access-Control-Allow-Methods","*");
//        responseHeaders.set("Access-Control-Allow-Origin", "*");

        return ResponseEntity.status(HttpStatus.CREATED)
//                .headers(responseHeaders)
                .body(cartItemDto);
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
            return ResponseEntity.ok(item);
        }
    }
}
