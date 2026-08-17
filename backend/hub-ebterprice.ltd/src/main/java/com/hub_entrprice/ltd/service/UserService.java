package com.hub_entrprice.ltd.service;

import com.hub_entrprice.ltd.Exceptions.UserAlreadyExistedException; 
import com.hub_entrprice.ltd.constants.AuthConstants;
import com.hub_entrprice.ltd.dto.UserDto;
import com.hub_entrprice.ltd.entity.User;
import com.hub_entrprice.ltd.enums.UserRole;
import com.hub_entrprice.ltd.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public Map<String, Object> createUser(UserDto user) {
        Optional<User> dbOptional=userRepository.findByEmail(user.getEmail());
        if(dbOptional.isPresent()){
            throw new UserAlreadyExistedException(AuthConstants.ERROR_USER_ALREADY_EXISTED);
        }else{
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setRole(UserRole.JOBSEAKER);

       newUser= userRepository.save(newUser);

       String token = jwtService.generateJwtToken(newUser);
       Map<String,Object> responseMap=new HashMap<>();
       responseMap.put("userData",user);
       responseMap.put("token", token);

       return responseMap;

        }
    }

}

