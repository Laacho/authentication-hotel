package com.tinqinacademy.authenticationHotel.api.models.operations.recoverPassword;

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
public class RecoverPasswordInput implements OperationRequest {
    @Size(min = 1, max = 30)
    @NotNull(message = "email cannot be null")
    private String email;
}
