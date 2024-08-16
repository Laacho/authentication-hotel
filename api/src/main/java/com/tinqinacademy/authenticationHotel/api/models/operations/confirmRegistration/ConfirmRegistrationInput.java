package com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmRegistrationInput implements OperationRequest {

    private String confirmationCode;
}
