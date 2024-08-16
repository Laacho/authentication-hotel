package com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatusCode;

import java.util.Set;

public interface ErrorHandler {

    ErrorWrapper handleError(Throwable t);
    ErrorWrapper handleViolations(Set<ConstraintViolation<OperationRequest>> violations, HttpStatusCode statusCode) ;

}
