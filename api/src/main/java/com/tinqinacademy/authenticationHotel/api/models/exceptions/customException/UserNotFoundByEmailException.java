package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class UserNotFoundByEmailException extends RuntimeException{
    public UserNotFoundByEmailException(String message) {
        super(message);
    }
}
