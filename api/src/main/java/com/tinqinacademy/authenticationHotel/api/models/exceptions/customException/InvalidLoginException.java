package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(String message) {
        super(message);
    }
}
