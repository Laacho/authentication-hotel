package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.DuplicateUsernameException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.EmailException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.core.service.utils.EmailService;
import com.tinqinacademy.authenticationHotel.core.service.utils.Generator;
import com.tinqinacademy.authenticationHotel.persistence.entities.BlacklistedCodes;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.repository.BlacklistedCodesRepository;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterUserProcessor extends BaseOperationProcessor implements RegisterUserOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BlacklistedCodesRepository blacklistedCodesRepository;
    private final Generator generator;
    private final ConversionService conversionService;
    private final EmailService emailService;

    public RegisterUserProcessor(Validator validator, ErrorHandler errorHandler,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 BlacklistedCodesRepository blacklistedCodesRepository,
                                 Generator generator, ConversionService conversionService, EmailService emailService) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.blacklistedCodesRepository = blacklistedCodesRepository;
        this.generator = generator;
        this.conversionService = conversionService;
        this.emailService = emailService;
    }

    @Override
    public Either<ErrorWrapper, RegisterUserOutput> process(RegisterUserInput input) {
        log.info("Start registering user input: {}", input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    checkForExistingUsername(input.getUsername());
                    checkForExistingEmail(input.getEmail());
                    User user = conversionService.convert(input, User.UserBuilder.class)
                            .password(passwordEncoder.encode(input.getPassword()))
                            .build();
                    User savedUsed = userRepository.save(user);
                    String randomCode = generator.generateRandomCode();
                    BlacklistedCodes accountConfirmationCode = BlacklistedCodes.builder()
                            .code(randomCode)
                            .email(savedUsed.getEmail())
                            .build();
                    blacklistedCodesRepository.save(accountConfirmationCode);

                    emailService.sendEmailForAccountActivation(
                            savedUsed.getFirstName(),
                            savedUsed.getEmail(),
                            randomCode);

                    RegisterUserOutput output = outputBuilder(savedUsed);
                    log.info("End of registering user output: {}", output);
                    return output;


                }).toEither()
                .mapLeft(errorHandler::handleError));

    }

    private RegisterUserOutput outputBuilder(User savedUsed) {
        return RegisterUserOutput.builder()
                .id(savedUsed.getUserId())
                .build();
    }

    private void checkForExistingEmail(String email) {
        boolean result = userRepository.existsByEmail(email);
        if (result) {
            throw new EmailException("Email: " + email + " already exists");
        }
    }

    private void checkForExistingUsername(String username) {
        boolean result = userRepository.existsByUsername(username);
        if (result) {
            throw new DuplicateUsernameException("Username: " + username + " already exists");
        }
    }

}
