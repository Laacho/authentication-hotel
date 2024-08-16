package com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoverPasswordOutput implements OperationResponse {
    private String message;
}
