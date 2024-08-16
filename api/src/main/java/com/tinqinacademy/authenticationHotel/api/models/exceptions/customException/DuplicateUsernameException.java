package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
