package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.DemotingException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidUserIdException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.demote.DemoteUserInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.demote.DemoteUserOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.demote.DemoteUserOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.enums.Role;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoteUserProcessor extends BaseOperationProcessor implements DemoteUserOperation {
    private final UserRepository userRepository;

    public DemoteUserProcessor(Validator validator, ErrorHandler errorHandler, UserRepository userRepository) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
    }

    @Override
    public Either<ErrorWrapper, DemoteUserOutput> process(DemoteUserInput input) {
        log.info("Start demoting user input: {}", input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                            User user = checkForUserRole(input);
                            user.setRole(Role.USER);
                            userRepository.save(user);
                            DemoteUserOutput output = outputBuilder();
                            log.info("End demoting user output: {}", output);
                            return output;
                        }).toEither()
                        .mapLeft(errorHandler::handleError)
        );
    }

    private DemoteUserOutput outputBuilder() {
        return DemoteUserOutput.builder()
                .message("Successfully demoted user")
                .build();
    }

    private User checkForUserRole(DemoteUserInput input) {
        User user = userRepository.findById(input.getUserId()).orElseThrow(() ->
                new InvalidUserIdException("User with this id does not exist"));

        if (user.getRole().toString().equals(Role.USER.toString())) {
            throw new DemotingException("User cannot be demoted");
        }
        return user;

    }
}
