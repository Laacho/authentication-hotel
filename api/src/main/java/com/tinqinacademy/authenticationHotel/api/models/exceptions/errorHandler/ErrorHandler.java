package com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;

public interface ErrorHandler {

    ErrorWrapper handleError(Throwable t);
}
