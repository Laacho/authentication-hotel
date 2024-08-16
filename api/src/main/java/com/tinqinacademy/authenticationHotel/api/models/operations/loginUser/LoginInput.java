package com.tinqinacademy.authenticationHotel.api.models.operations.loginUser;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginInput implements OperationRequest {
    private String username;
    private String password;
}
