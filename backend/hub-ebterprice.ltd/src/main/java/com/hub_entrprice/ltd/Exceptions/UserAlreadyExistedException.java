package com.hub_entrprice.ltd.Exceptions;

public class UserAlreadyExistedException extends RuntimeException {
    public UserAlreadyExistedException(String message){
        super(message);
    }
}
