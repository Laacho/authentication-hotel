package com.tinqinacademy.authenticationHotel.core.service;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import io.vavr.control.Either;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BaseOperationProcessor {
    private final Validator validator;
    protected final ErrorHandler errorHandler;


    public BaseOperationProcessor(Validator validator, ErrorHandler errorHandler) {
        this.validator = validator;
        this.errorHandler = errorHandler;
    }

    public Either<ErrorWrapper, ? extends OperationRequest> validateInput(OperationRequest input) {
        Set<ConstraintViolation<OperationRequest>> constraintViolations = validator.validate(input);

        if (constraintViolations.isEmpty()) {
            return Either.right(input);
        }

        ErrorWrapper errors = errorHandler.handleViolations(constraintViolations, HttpStatus.BAD_REQUEST);
        return Either.left(errors);
    }
}
