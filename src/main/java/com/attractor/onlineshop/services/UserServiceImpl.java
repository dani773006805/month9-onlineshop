package com.attractor.onlineshop.services;

import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.exceptions.UserNotFoundException;
import com.attractor.onlineshop.model.Role;
import com.attractor.onlineshop.model.User;
import com.attractor.onlineshop.repositories.RoleRepository;
import com.attractor.onlineshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private ShoppingCartServiceImpl shoppingCartService;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartServiceImpl shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User save(User user) {
        var enc = passwordEncoder.encode(user.getPassword());
        user.setPassword(enc);
        user.setRoles((List<Role>) roleRepository.findByName("USER").orElseThrow(()->new ResourceNotFoundException("No role")));
        var savedUser = userRepository.save(user);
        shoppingCartService.create(savedUser);
        return savedUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s doesn't exist", s)));
    }
}
