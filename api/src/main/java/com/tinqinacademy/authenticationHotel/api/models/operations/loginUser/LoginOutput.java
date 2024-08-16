package com.tinqinacademy.authenticationHotel.api.models.operations.loginUser;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginOutput implements OperationResponse {
    private String jwt;
}
