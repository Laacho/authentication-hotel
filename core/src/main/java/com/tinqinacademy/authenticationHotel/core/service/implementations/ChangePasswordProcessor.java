package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.EmailException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidUserIdException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.PasswordException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.changePassword.ChangePasswordInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.changePassword.ChangePasswordOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.changePassword.ChangePasswordOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ChangePasswordProcessor extends BaseOperationProcessor implements ChangePasswordOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ChangePasswordProcessor(Validator validator, ErrorHandler errorHandler, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Either<ErrorWrapper, ChangePasswordOutput> process(ChangePasswordInput input) {
        log.info("Start  change password operation input: {}",input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    User loggedUser = getLoggedUser(input);
                    checkIfPasswordMatches(input, loggedUser);
                    checkIfEmailMatches(input, loggedUser);
                    checkIfNewPasswordIsTheSameAsOldOne(input);

                    User user = getUserByEmail(input);
                    user.setPassword(passwordEncoder.encode(input.getNewPassword()));
                    userRepository.save(user);
                    ChangePasswordOutput output = outputBuilder();
                    log.info("Finish change password operation output: {}",output);
                    return output;
                        }).toEither()
                        .mapLeft(errorHandler::handleError)
        );
    }

    private User getUserByEmail(ChangePasswordInput input) {
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EmailException("Invalid email!"));
    }

    private User getLoggedUser(ChangePasswordInput input) {
        return userRepository.findById(UUID.fromString(input.getUserID())).orElseThrow(
                () -> new InvalidUserIdException("Logged user not found")
        );
    }
    private void checkIfPasswordMatches(ChangePasswordInput input, User loggedUser) {
        if (!passwordEncoder.matches(input.getOldPassword(), loggedUser.getPassword())) {
            throw new PasswordException("Passwords do not match");
        }
    }
    private void checkIfEmailMatches(ChangePasswordInput input, User loggedUser) {
        if (!loggedUser.getEmail().equals(input.getEmail())) {
            throw new EmailException("Email provided does not match");
        }
    }
    private void checkIfNewPasswordIsTheSameAsOldOne(ChangePasswordInput input) {
        if (input.getOldPassword().equals(input.getNewPassword())) {
            throw new PasswordException("New password cannot be the same as the old one");
        }
    }
    private  ChangePasswordOutput outputBuilder() {
        return ChangePasswordOutput.builder()
                        .message("Successfully changed password!")
                        .build();
    }
}
