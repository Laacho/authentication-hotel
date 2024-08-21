package com.tinqinacademy.authenticationHotel.api.models.operations.loginUser;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginInput implements OperationRequest {
    @Size(min = 1, max = 50)
    @NotNull(message = "username cannot be null")
    private String username;
    @Size(min = 1, max = 50)
    @NotNull(message = "password cannot be null")
    private String password;
}
