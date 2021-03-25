package com.attractor.onlineshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
//    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
@GetMapping
    public ResponseEntity<String> auth(@AuthenticationPrincipal OidcUser user) {
        var l = user.getEmail();
        return ResponseEntity.ok(l);
    }


}
