package com.tinqinacademy.authenticationHotel.core.service.implementations;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.AccountNotConfirmedException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidLoginException;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginOutput;
import com.tinqinacademy.authenticationHotel.core.service.BaseOperationProcessor;
import com.tinqinacademy.authenticationHotel.core.service.jwt.JWTService;
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
public class LoginUserProcessor extends BaseOperationProcessor implements LoginOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public LoginUserProcessor(Validator validator, ErrorHandler errorHandler, UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        super(validator, errorHandler);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public Either<ErrorWrapper, LoginOutput> process(LoginInput input) {
        log.info("Start loginUser input: {}",input);
        return validateInput(input).flatMap(validatedInput -> Try.of(() -> {
                    User user = checkForValidCredentials(input);
                    checkIfUserIsConfirmed(user);
                    String jwt = jwtService.generateToken(user);
                    LoginOutput output = LoginOutput.builder()
                            .jwt(jwt)
                            .build();
                    log.info("End loginUser output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(errorHandler::handleError));
    }

    private void checkIfUserIsConfirmed(User user) {
        if(!user.getIsConfirmed()){
            throw new AccountNotConfirmedException("User is not confirmed!");
        }
    }

    private User checkForValidCredentials(LoginInput input) {
        User user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new InvalidLoginException("Invalid username"));
        if(!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new InvalidLoginException("Invalid password");
        }
        return user;
    }
}
