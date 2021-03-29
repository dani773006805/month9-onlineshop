package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.CardDetailsDto;
import com.attractor.onlineshop.dto.mapper.CardDetailsMapper;
import com.attractor.onlineshop.dto.mapper.OrderDtoMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.services.CardDetailsService;
import com.attractor.onlineshop.services.OrderItemService;
import com.attractor.onlineshop.services.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.stream.Collectors;

@RequestMapping("/orders")
@RestController
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private CardDetailsService cardDetailsService;

    public OrderController(OrderService orderService, OrderItemService orderItemService, CardDetailsService cardDetailsService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.cardDetailsService = cardDetailsService;
    }

    @GetMapping
    public ResponseEntity<?> getUserOrders(Principal user, Pageable pageable) {
        if (user == null) {
            throw new ResourceNotFoundException("user email is null");
        }
        var orders = orderService.findByUserEmail(user.getName());
        var orderDtos=orders.stream().map(OrderDtoMapper::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping("/purchase")
    @Transactional
    public ResponseEntity<?> saveOrder(@RequestBody  CardDetailsDto cardDetailsDto, Principal user) {
        if (user.getName() == null) {
            throw new ResourceNotFoundException("user email is null");
        }
        if (cardDetailsDto.getId() == null) {
            var card = CardDetailsMapper.fromDtoToEnt(cardDetailsDto);
            card.setUserEmail(user.getName());
            cardDetailsService.save(card);
        }
        orderService.save(user.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }
    @GetMapping("/credit-card")
    public ResponseEntity<?> getCard(Principal user){
        if(user.getName()==null){
            throw new ResourceNotFoundException("user email is null");
        }
        var cart=cardDetailsService.findByEmail(user.getName()).orElseThrow(()->
                new ResourceNotFoundException("card doesn't exist"));
        var cardDTo=CardDetailsMapper.fromEntToDto(cart);
        return ResponseEntity.ok(cardDTo);
    }
}
