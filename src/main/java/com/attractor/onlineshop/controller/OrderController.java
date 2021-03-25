package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.CardDetailsDto;
import com.attractor.onlineshop.dto.OrderDto;
import com.attractor.onlineshop.dto.mapper.CardDetailsMapper;
import com.attractor.onlineshop.dto.mapper.OrderDtoMapper;
import com.attractor.onlineshop.dto.mapper.OrderItemDtoMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.services.CardDetailsService;
import com.attractor.onlineshop.services.OrderItemService;
import com.attractor.onlineshop.services.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200")
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
        List<OrderDto> orderDtos = new ArrayList<>();
        var orders = orderService.findByUserEmail(user.getName());
        for (int i = 0; i < orders.size(); i++) {
            var orderDto = OrderDtoMapper.from(orders.get(i));
            var orderItemDto = orderItemService.findByOrderId(orderDto.getId())
                    .stream().map(OrderItemDtoMapper::fromAll).collect(Collectors.toList());
            orderDto.setOrderItemDtos(orderItemDto);
            orderDtos.add(orderDto);
        }
        return ResponseEntity.ok(orderDtos);
    }
//    @GetMapping("/shop")
//    public ResponseEntity<?> getUserOrders(){
//
//        List<OrderDto> orderDtos=new ArrayList<>();
//        var orders=orderService.findByUserEmail("sdk");
//        for(int i=0;i<orders.size();i++){
//            var orderDto= OrderDtoMapper.from(orders.get(i));
//            var orderItemDto=orderItemService.findByOrderId(orderDto.getId())
//                    .stream().map(OrderItemDtoMapper::fromAll).collect(Collectors.toList());
//            orderDto.setOrderItemDtos(orderItemDto);
//            orderDtos.add(orderDto);
//        }
//        return ResponseEntity.ok(orderDtos);
//    }

    @PostMapping("/purchase")
    public ResponseEntity<?> saveOrder(@RequestBody @Valid CardDetailsDto cardDetailsDto, Principal user) {
        if (user.getName() == null) {
            throw new ResourceNotFoundException("user email is null");
        }
        if (cardDetailsDto.getId() == null) {
            var card = CardDetailsMapper.fromDtoToEnt(cardDetailsDto);
            card.setUserEmail(user.getName());
            cardDetailsService.save(card);
        }
        var order = OrderDtoMapper.from(orderService.save(user.getName()));
        var orderItemDto = orderItemService.findByOrderId(order.getId())
                .stream().map(OrderItemDtoMapper::fromAll).collect(Collectors.toList());
        order.setOrderItemDtos(orderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
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
