package com.hub_entrprice.ltd.controller;

import com.hub_entrprice.ltd.dto.UserDto;
import com.hub_entrprice.ltd.entity.User;
import com.hub_entrprice.ltd.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = userService.createUser(user);
        return ResponseEntity.ok(result);
    }
     @GetMapping("/login")
     public ResponseEntity<User> loginUser(@Valid @RequestBody UserDto user) {
        userService.loginUser(user);
        return ResponseEntity.ok().build();
}
}
    
