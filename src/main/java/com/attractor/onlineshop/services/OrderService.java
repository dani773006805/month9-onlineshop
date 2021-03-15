package com.attractor.onlineshop.services;

import com.attractor.onlineshop.dto.mapper.CardItemOrderMapper;
import com.attractor.onlineshop.exceptions.QuantityErrorException;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.model.Order;
import com.attractor.onlineshop.model.OrderItem;
import com.attractor.onlineshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private UserService userService;
    private OrderRepository orderRepository;
    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private ShopCartItemService shopCartItemService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setShopCartItemService(ShopCartItemService shopCartItemService) {
        this.shopCartItemService = shopCartItemService;
    }

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order save(Long userId) {

        var shopCart = shoppingCartService.findByUserId(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("user with id %d not found", userId)));
        var order = CardItemOrderMapper.shopCartToOrder(shopCart);
        order.setUser(userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", userId))));
        order.setDateCreated(LocalDateTime.now());
        var orderItems = shopCartItemService.findByCartId(shopCart.getId())
                .stream().map(shopingCartItem -> CardItemOrderMapper.shopItemToOrderItem(shopingCartItem))
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);
        changeProductQuantity(orderItems);
        var savedOrder = orderRepository.save(order);
        shopCartItemService.deleteByCartId(shopCart.getId());
        return savedOrder;
    }

    private void changeProductQuantity(List<OrderItem> orderItems) {
        for (int i = 0; i < orderItems.size(); i++) {
            int finalI = i;
            var product = productService.findById(orderItems.get(i).getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", orderItems.get(finalI).getProductId())));
            if (product.getUnitsInStock() < orderItems.get(i).getUnitsI()) {
                throw new QuantityErrorException(String.format("Product with id %d has less units in stock"));
            }
            product.setUnitsInStock(product.getUnitsInStock() - orderItems.get(i).getUnitsI());
            if(product.getUnitsInStock()==0){
                product.setActive(false);
            }
            productService.update(product);
        }
    }
}
