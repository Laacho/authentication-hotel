package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.EmailException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword.RecoverPasswordInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword.RecoverPasswordOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword.RecoverPasswordOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.core.service.utils.EmailService;
import com.tinqinacademy.authenticationHotel.core.service.utils.Generator;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecoverPasswordProcessor extends BaseOperationProcessor implements RecoverPasswordOperation {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Generator generator;
    private final PasswordEncoder passwordEncoder;

    public RecoverPasswordProcessor(Validator validator, ErrorHandler errorHandler, UserRepository userRepository, EmailService emailService, Generator generator,  PasswordEncoder passwordEncoder) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Either<ErrorWrapper, RecoverPasswordOutput> process(RecoverPasswordInput input) {
        log.info("Start recover password process with input: {}",input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    User user = findUserByEmail(input);
                        String passwordRecoveryCode = generator.generatePasswordRecoveryCode();
                            user.setPassword(passwordEncoder.encode(passwordRecoveryCode));
                            userRepository.save(user);
                        emailService.sendEmailWithNewPassword(
                                user.getFirstName(),
                                user.getEmail(),
                                passwordRecoveryCode);
                    RecoverPasswordOutput output = outputBuilder();
                    log.info("End recoverPassword output: {}",output);
                    return output;
                }).toEither()
                .mapLeft(errorHandler::handleError));
    }

    private User findUserByEmail(RecoverPasswordInput input) {
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EmailException("Invalid email"));
    }

    private  RecoverPasswordOutput outputBuilder() {
        return RecoverPasswordOutput.builder()
                .message("Successfully recovered password!")
                .build();
    }
}
