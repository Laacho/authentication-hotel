package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
