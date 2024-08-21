package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}

