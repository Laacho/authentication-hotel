package com.tinqinacademy.authenticationHotel.api.models.operations.changePassword;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordInput implements OperationRequest {
    private String oldPassword;
    private String newPassword;
    private String email;
}
