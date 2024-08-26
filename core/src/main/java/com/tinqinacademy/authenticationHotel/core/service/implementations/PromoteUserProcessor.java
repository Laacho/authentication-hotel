package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidUserIdException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.PromotingException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.promote.PromoteUserInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.promote.PromoteUserOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.promote.PromoteUserOutput;
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
public class PromoteUserProcessor extends BaseOperationProcessor implements PromoteUserOperation {
    private final UserRepository userRepository;

    public PromoteUserProcessor(Validator validator, ErrorHandler errorHandler, UserRepository userRepository) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
    }

    @Override
    public Either<ErrorWrapper, PromoteUserOutput> process(PromoteUserInput input) {
        log.info("Start promoting user input :{}", input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    User user = userRepository.findById(input.getUserId()).orElseThrow(() -> new InvalidUserIdException("User with this ID doesnt exists"));
                    if(user.getRole().toString().equals(Role.ADMIN.toString())){
                        throw new PromotingException("User is already an Admin");
                    }
                    user.setRole(Role.ADMIN);
                    userRepository.save(user);
                    PromoteUserOutput output = PromoteUserOutput.builder()
                                    .message("Successfully promoted user")
                                    .build();
                    log.info("Finish promoting user output :{}", output);
                    return output;
                        }).toEither()
                        .mapLeft(errorHandler::handleError)
        );
    }
}
