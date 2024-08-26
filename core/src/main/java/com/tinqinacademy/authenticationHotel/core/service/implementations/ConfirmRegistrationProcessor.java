package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.AccountAlreadyConfirmedException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.UserNotFoundByEmailException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration.ConfirmRegistrationInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration.ConfirmRegistrationOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration.ConfirmRegistrationOutput;
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

@Service
@Slf4j
public class ConfirmRegistrationProcessor extends BaseOperationProcessor implements ConfirmRegistrationOperation {
    private final UserRepository userRepository;
    private final BlacklistedCodesRepository blacklistedCodesRepository;


    public ConfirmRegistrationProcessor(Validator validator, ErrorHandler errorHandler, UserRepository userRepository, BlacklistedCodesRepository blacklistedCodesRepository) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
        this.blacklistedCodesRepository = blacklistedCodesRepository;
    }

    @Override
    public Either<ErrorWrapper, ConfirmRegistrationOutput> process(ConfirmRegistrationInput input) {
        log.info("Start confirmingRegistration input: {}", input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                            User user = checkForAccountConfirmation(input.getConfirmationCode());
                            blacklistedCodesRepository.deleteByEmail(user.getEmail());

                            user.setIsConfirmed(true);
                            userRepository.save(user);
                    ConfirmRegistrationOutput output = outputBuilder();
                    log.info("End confirmingRegistration output: {}", output);
                            return output;
                        }).toEither()
                        .mapLeft(errorHandler::handleError)
        );
    }

    private  ConfirmRegistrationOutput outputBuilder() {
        return ConfirmRegistrationOutput.builder()
                .message("Successfully confirmed")
                .build();
    }

    private User checkForAccountConfirmation(String confirmationCode) {

        BlacklistedCodes code = blacklistedCodesRepository.findByCode(confirmationCode)
                .orElseThrow(() -> new AccountAlreadyConfirmedException("Account is already confirmed"));
        User user = userRepository.findByEmail(code.getEmail())
                .orElseThrow(() -> new UserNotFoundByEmailException("Not a valid email!"));
        if (user.getIsConfirmed()) {
            throw new AccountAlreadyConfirmedException("Account is already confirmed");
        }
        return user;

    }
}
