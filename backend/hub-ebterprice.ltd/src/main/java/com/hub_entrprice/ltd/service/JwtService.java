package com.hub_entrprice.ltd.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hub_entrprice.ltd.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secreat.key}")
    private String jwtSecreatKey;

    private int JWT_TOKEN_VALIDITY=24*60*60*1000;

    private Key generatesecreatKey(){
        return Keys.hmacShaKeyFor(jwtSecreatKey.getBytes());
        
    }

    public String generateJwtToken(User userData){

        Date tokenGeneratedTime= new Date();

        Date expiryDate= new Date(tokenGeneratedTime.getTime() +JWT_TOKEN_VALIDITY);

        Map<String,Object> tokenData= new HashMap<>();
        tokenData.put("id",userData.getId());
        tokenData.put("name", userData.getFirstName());
        tokenData.put("email",userData.getEmail());
        tokenData.put("role",userData.getRole());

        String jwtToken = Jwts.builder().claims()
                          .add(tokenData).and()
                          .subject(userData.getEmail())
                          .issuedAt(tokenGeneratedTime)
                          .expiration(expiryDate)
                          .signWith(generatesecreatKey())
                          .compact();

        return jwtToken;
        
    }
}
