package com.tinqinacademy.authenticationHotel.api.models.operations.changePassword;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordInput implements OperationRequest {
    @Size(min = 1, max = 15)
    @NotNull(message = "old password cannot be null")
    private String oldPassword;
    @Size(min = 1, max = 15)
    @NotNull(message = "new password cannot be null")
    private String newPassword;
    @Size(min = 1, max = 30)
    @NotNull(message = "email cannot be null")
    private String email;

    @Hidden
    @JsonIgnore
    private String userID;
}
