package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.core.service.jwt.JWTService;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateJWTProcessor extends BaseOperationProcessor implements ValidateJWTOperation {
    private final JWTService jwtService;

    public ValidateJWTProcessor(Validator validator, ErrorHandler errorHandler, JWTService jwtService) {
        super(validator, errorHandler);
        this.jwtService = jwtService;
    }

    @Override
    public Either<ErrorWrapper, ValidateJWTOutput> process(ValidateJWTInput input) {
        log.info("Start validateToken input {}",input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    Boolean b = jwtService.validateJwt(input.getJwt());
                    ValidateJWTOutput output = ValidateJWTOutput.builder()
                                    .validatedToken(b)
                                    .build();
                    log.info("End of validateToken output {}",output);
                    return output;
                        }).toEither()
                        .mapLeft(errorHandler::handleError)
        );
    }
}
