package com.tinqinacademy.authenticationHotel.api.models.operations.changePassword;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordOutput implements OperationResponse {
    private String message;
}
