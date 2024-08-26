package com.tinqinacademy.authenticationHotel.rest.authController;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.changePassword.ChangePasswordInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.changePassword.ChangePasswordOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.changePassword.ChangePasswordOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration.ConfirmRegistrationInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration.ConfirmRegistrationOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration.ConfirmRegistrationOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.demote.DemoteUserInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.demote.DemoteUserOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.demote.DemoteUserOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.logout.LogoutInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.logout.LogoutOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.logout.LogoutOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.promote.PromoteUserInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.promote.PromoteUserOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.promote.PromoteUserOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword.RecoverPasswordInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword.RecoverPasswordOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword.RecoverPasswordOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserOutput;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTOperation;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTOutput;
import com.tinqinacademy.authenticationHotel.core.service.paths.AuthURLPaths;
import com.tinqinacademy.authenticationHotel.rest.baseController.BaseController;
import com.tinqinacademy.authenticationHotel.rest.config.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.vavr.control.Either;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController extends BaseController {
        private final RegisterUserOperation registerUserOperation;
        private final ConfirmRegistrationOperation confirmRegistrationOperation;
        private final LoginOperation loginOperation;
        private final RecoverPasswordOperation recoverPasswordOperation;
        private final PromoteUserOperation promoteUserOperation;
        private final DemoteUserOperation demoteUserOperation;
        private final ChangePasswordOperation changePasswordOperation;
        private final LogoutOperation logoutOperation;
        private final UserContext userContext;
    private final ValidateJWTOperation validateJWTOperation;


    public AuthenticationController(RegisterUserOperation registerUserOperation, ConfirmRegistrationOperation confirmRegistrationOperation, LoginOperation loginOperation, RecoverPasswordOperation recoverPasswordOperation, PromoteUserOperation promoteUserOperation, DemoteUserOperation demoteUserOperation, ChangePasswordOperation changePasswordOperation, LogoutOperation logoutOperation, UserContext userContext, ValidateJWTOperation validateJWTOperation) {
        this.registerUserOperation = registerUserOperation;
        this.confirmRegistrationOperation = confirmRegistrationOperation;
        this.loginOperation = loginOperation;
        this.recoverPasswordOperation = recoverPasswordOperation;
        this.promoteUserOperation = promoteUserOperation;
        this.demoteUserOperation = demoteUserOperation;
        this.changePasswordOperation = changePasswordOperation;
        this.logoutOperation = logoutOperation;
        this.userContext = userContext;
        this.validateJWTOperation = validateJWTOperation;
    }

    @PostMapping(AuthURLPaths.REGISTER)
    @Operation(summary = "registers Done")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserInput input) {
        Either<ErrorWrapper, RegisterUserOutput> result = registerUserOperation.process(input);
        return handleResponse(result);
    }

    @PostMapping(AuthURLPaths.CONFIRM_REGISTRATION)
    @Operation(summary = "confirming registration done")
    public ResponseEntity<?> confirmationRegistration(@RequestBody ConfirmRegistrationInput input) {
        Either<ErrorWrapper, ConfirmRegistrationOutput> process = confirmRegistrationOperation.process(input);
        return handleResponse(process);
    }

    @PostMapping(AuthURLPaths.LOGIN)
    @Operation(summary = "login user done")
    public ResponseEntity<?> login(@RequestBody LoginInput input) {
        Either<ErrorWrapper, LoginOutput> process = loginOperation.process(input);
        if(process.isRight()) {
            LoginOutput output = process.get();
          HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + output.getJwt());
            return ResponseEntity.ok().headers(headers).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(process.getLeft());
    }

    @PostMapping(AuthURLPaths.RECOVER_PASSWORD)
    @Operation(summary = "recover password done")
    public ResponseEntity<?> recoverPassword(@RequestBody RecoverPasswordInput input) {
        Either<ErrorWrapper, RecoverPasswordOutput> process =
                recoverPasswordOperation.process(input);
        return ResponseEntity.ok(process.get());
    }

    @PostMapping(AuthURLPaths.PROMOTE)
    @Operation(summary = "promote user done")
    public ResponseEntity<?> promoteUser(@RequestBody PromoteUserInput input) {
        Either<ErrorWrapper, PromoteUserOutput> process =
                promoteUserOperation.process(input);
        return handleResponse(process);
    }


    @PostMapping(AuthURLPaths.DEMOTE)
    @Operation(summary = "demoting user done")
    public ResponseEntity<?> demoteUser(@RequestBody DemoteUserInput input) {
        Either<ErrorWrapper, DemoteUserOutput> process = demoteUserOperation.process(input);
        return handleResponse(process);
    }

    @PostMapping(AuthURLPaths.CHANGE_PASSWORD)
    @Operation(summary = "change password for user done")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordInput input){
        ChangePasswordInput request = input.toBuilder()
                .userID(userContext.getCurrUser().getUserId().toString())
                .build();
        Either<ErrorWrapper, ChangePasswordOutput> process =
                changePasswordOperation.process(request);
        return handleResponse(process);
    }
    @PostMapping(AuthURLPaths.LOGOUT)
    @Operation(summary = "logout")
    public ResponseEntity<?> logout(){
        LogoutInput input = LogoutInput.builder()
                .userContextId(userContext.getCurrUser().getUserId().toString())
                .jwt(userContext.getJwtToken())
                .build();
        Either<ErrorWrapper, LogoutOutput> process = logoutOperation.process(input);
        return handleResponse(process);
    }
    public ResponseEntity<?> validateJWT(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String substring = authorizationHeader.substring(7);
        ValidateJWTInput input = ValidateJWTInput.builder()
                .jwt(substring)
                .build();
        Either<ErrorWrapper, ValidateJWTOutput> process = validateJWTOperation.process(input);
        return handleResponse(process);
    }
}
