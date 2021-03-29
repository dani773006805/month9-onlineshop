package com.attractor.onlineshop.services;

import com.attractor.onlineshop.dto.mapper.CardItemOrderMapper;
import com.attractor.onlineshop.exceptions.QuantityErrorException;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.model.Order;
import com.attractor.onlineshop.model.OrderItem;
import com.attractor.onlineshop.repositories.OrderItemRepository;
import com.attractor.onlineshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;
    private ShoppingCartServiceImpl shoppingCartService;
    private ShopCartItemServiceImpl shopCartItemService;
    private OrderItemRepository orderItemRepository;
@Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
@Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Autowired
    public void setProductService(ProductService productServiceImpl) {
        this.productService = productServiceImpl;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartServiceImpl shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setShopCartItemService(ShopCartItemServiceImpl shopCartItemService) {
        this.shopCartItemService = shopCartItemService;
    }

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findByUserEmail(String email) {
        return orderRepository.findAllByUserEmail(email);
    }

    public Order save(String email) {
        var shopCart = shoppingCartService.findByUserEmail(email).orElseThrow(
                () -> new UserNotFoundException(String.format("user with id %s not found", email)));
        var order = CardItemOrderMapper.shopCartToOrder(shopCart);
        order.setUserEmail(email);
        order.setDateCreated(LocalDateTime.now());
        var orderItems = shopCartItemService.findByCartId(shopCart.getId())
                .stream().map(shopingCartItem -> CardItemOrderMapper.shopItemToOrderItem(shopingCartItem))
                .collect(Collectors.toList());
        changeProductQuantity(orderItems);
        var savedOrder = orderRepository.save(order);
        orderItems.forEach(orderItem -> orderItem.setOrder(savedOrder));
        orderItemRepository.saveAll(orderItems);
        shopCartItemService.deleteByCartId(shopCart.getId());
        shoppingCartService.updateStatus(shopCart);
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
            if (product.getUnitsInStock() == 0) {
                product.setActive(false);
            }
            productService.update(product);
        }
    }
}
