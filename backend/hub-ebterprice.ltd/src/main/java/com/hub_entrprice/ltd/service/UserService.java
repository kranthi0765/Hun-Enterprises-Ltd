package com.hub_entrprice.ltd.service;

import com.hub_entrprice.ltd.entity.User;
import com.hub_entrprice.ltd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

