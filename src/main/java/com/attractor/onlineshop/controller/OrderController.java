package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.CardDetailsDto;
import com.attractor.onlineshop.dto.OrderDto;
import com.attractor.onlineshop.dto.mapper.CardDetailsMapper;
import com.attractor.onlineshop.dto.mapper.OrderDtoMapper;
import com.attractor.onlineshop.dto.mapper.OrderItemDtoMapper;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/orders")
@RestController
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private UserService userService;
    private CardDetailsService cardDetailsService;

    public OrderController(OrderService orderService, OrderItemService orderItemService, UserService userService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.userService = userService;
    }
@Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<?> getUserOrders(){
        var userId=0L;
        List<OrderDto> orderDtos=new ArrayList<>();
        var orders=orderService.findByUserId(userId);
        for(int i=0;i<orders.size();i++){
            var orderDto= OrderDtoMapper.from(orders.get(i));
            var orderItemDto=orderItemService.findByOrderId(orderDto.getId())
                    .stream().map(OrderItemDtoMapper::fromAll).collect(Collectors.toList());
            orderDto.setOrderItemDtos(orderItemDto);
            orderDtos.add(orderDto);
        }
        return ResponseEntity.ok(orderDtos);
    }

    @Secured("ROLE_USER")
    @PostMapping("/purchase")
    public ResponseEntity<?> saveOrder(@RequestBody @Valid CardDetailsDto cardDetailsDto){
        var userId=1L;
        if(cardDetailsDto.getId()==null){
            var user= userService.findById(userId).orElseThrow(()->
                    new UserNotFoundException(String.format("User with id %d not found")));
            var card= CardDetailsMapper.fromDtoToEnt(cardDetailsDto);
            card.setUser(user);
            cardDetailsService.save(card);
        }
        var order=OrderDtoMapper.from(orderService.save(userId));
        var orderItemDto=orderItemService.findByOrderId(order.getId())
                .stream().map(OrderItemDtoMapper::fromAll).collect(Collectors.toList());
        order.setOrderItemDtos(orderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);


    }
}
