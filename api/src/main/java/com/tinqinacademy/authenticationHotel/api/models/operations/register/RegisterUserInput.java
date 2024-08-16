package com.tinqinacademy.authenticationHotel.api.models.operations.register;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserInput implements OperationRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;



}
