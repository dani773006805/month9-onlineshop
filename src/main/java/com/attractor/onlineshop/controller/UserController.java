package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.UserDto;
import com.attractor.onlineshop.dto.mapper.UserMapper;
import com.attractor.onlineshop.model.User;
import com.attractor.onlineshop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<String> auth() {
        var l = "You are authenticated";
        return ResponseEntity.ok(l);
    }
    public ResponseEntity<?> save(@RequestBody @Valid UserDto userDto){
        var user= UserMapper.fromDto(userDto);
        var savedUser=UserMapper.toDto(userService.save(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
