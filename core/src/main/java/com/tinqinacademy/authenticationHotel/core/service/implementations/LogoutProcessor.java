package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidJWTException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidUserIdException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.logout.LogoutInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.logout.LogoutOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.logout.LogoutOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.persistence.entities.BlacklistedCodes;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.repository.BlacklistedCodesRepository;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class LogoutProcessor extends BaseOperationProcessor implements LogoutOperation {
    private final BlacklistedCodesRepository blacklistedCodesRepository;
    private final UserRepository userRepository;
    public LogoutProcessor(Validator validator, ErrorHandler errorHandler, BlacklistedCodesRepository blacklistedCodesRepository, UserRepository userRepository) {
        super(validator, errorHandler);
        this.blacklistedCodesRepository = blacklistedCodesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Either<ErrorWrapper, LogoutOutput> process(LogoutInput input) {
        log.info("Start logout user input: {}", input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    checkIfTokenIsBlacklisted(input);
                            User user = userRepository.findById(UUID.fromString(input.getUserContextId()))
                                    .orElseThrow(() -> new InvalidUserIdException("User id is invalid"));
                            BlacklistedCodes blacklistedCodes = BlacklistedCodes.builder()
                                    .email(user.getEmail())
                                    .code(input.getJwt())
                                    .build();
                            blacklistedCodesRepository.save(blacklistedCodes);
                            LogoutOutput output = LogoutOutput.builder()
                                    .message("Successfully logged out user")
                                    .build();
                            log.info("End logoutUser output: {}",output);
                            return output;
                        }).toEither()
                        .mapLeft(errorHandler::handleError)
        );
    }

    private void checkIfTokenIsBlacklisted(LogoutInput input) {
        Optional<BlacklistedCodes> byCode = blacklistedCodesRepository.findByCode(input.getJwt());
        if(byCode.isPresent()) {
             throw new InvalidJWTException("Already logged out user");
        }
    }
}
