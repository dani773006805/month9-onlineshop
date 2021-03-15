package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.User;
import com.attractor.onlineshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
private ShoppingCartService shoppingCartService;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
@Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
    public User save(User user){
        var savedUser=userRepository.save(user);
        shoppingCartService.create(savedUser);
        return savedUser;
    }
}
