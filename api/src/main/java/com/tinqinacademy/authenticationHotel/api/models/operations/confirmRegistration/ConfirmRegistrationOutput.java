package com.tinqinacademy.authenticationHotel.api.models.operations.confirmRegistration;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmRegistrationOutput implements OperationResponse {
    private String message;

}
