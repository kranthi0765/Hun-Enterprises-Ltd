package com.hub_entrprice.ltd.controller;

import com.hub_entrprice.ltd.apiResponse.ApiResponse;
import com.hub_entrprice.ltd.constants.AuthConstants;
import com.hub_entrprice.ltd.dto.UserDto;
import com.hub_entrprice.ltd.entity.User;
import com.hub_entrprice.ltd.service.UserService;

import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> registerUser(@Valid @RequestBody UserDto user) {
        Map<String,Object> sUser = userService.createUser(user);
        ApiResponse<User> apiResponse=new ApiResponse<User>(true, AuthConstants.USER_CREATED_SUCCESSFULLY, (User) sUser.get("userData"));
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Authorization", sUser.get("token").toString());
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
    }
}

    
