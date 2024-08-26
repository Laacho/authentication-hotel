package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException(String message) {
        super(message);
    }
}
