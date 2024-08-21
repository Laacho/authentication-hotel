package com.tinqinacademy.authenticationHotel.api.models.exceptions.customException;

public class AccountNotConfirmedException extends RuntimeException {
    public AccountNotConfirmedException(String message) {
        super(message);
    }
}
