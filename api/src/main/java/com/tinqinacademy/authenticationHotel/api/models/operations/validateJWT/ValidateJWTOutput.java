package com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateJWTOutput implements OperationResponse {
    private Boolean validatedToken;
}
