package com.hub_entrprice.ltd.service;

import com.hub_entrprice.ltd.dto.UserDto;
import com.hub_entrprice.ltd.entity.User;
import com.hub_entrprice.ltd.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public String createUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        userRepository.save(newUser);
        return "User created successfully"+
                "Username: " + newUser.getUsername() +
                ", Email: " + newUser.getEmail();
    }

    public ResponseEntity<User> loginUser(UserDto uuser) {
        User existingUser = null;
        try {
            existingUser = userRepository.findByEmail(uuser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (existingUser != null && existingUser.getPassword().equals(uuser.getPassword())) {
            // User authenticated successfully
            System.out.println("Login successful for user: " + existingUser);
        } else {
            // Invalid credentials
            System.out.println("Invalid email or password");
        }
        return ResponseEntity.ok(existingUser) ;
    }
    @GetMapping("/login/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();   
        }
    }

}

