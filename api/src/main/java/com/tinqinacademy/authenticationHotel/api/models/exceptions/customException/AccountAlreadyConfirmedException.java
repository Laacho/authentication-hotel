package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class AccountAlreadyConfirmedException extends RuntimeException{
    public AccountAlreadyConfirmedException(String message) {
        super(message);
    }
}
