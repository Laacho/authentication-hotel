package com.tinqinacademy.authenticationHotel.api.models.operations.logout;

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
public class LogoutInput implements OperationRequest {
    @Size(min = 1)
    @NotNull(message = "jwt token cannot be null")
    private String jwt;

    @Size(min = 1)
    @NotNull(message = "userContextId  cannot be null")
    private String userContextId;
}
