package com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoverPasswordInput implements OperationRequest {
    private String email;
}
