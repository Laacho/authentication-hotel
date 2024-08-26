package com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidateJWTInput implements OperationRequest{

    @NotBlank(message = "jwt cannot be blank")
    private String jwt;
}
