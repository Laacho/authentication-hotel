package com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmRegistrationInput implements OperationRequest {
    @Size(min = 1)
    @NotNull(message = "confirmation code cannot be null")
    private String confirmationCode;
}
