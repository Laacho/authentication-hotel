package com.tinqinacademy.authenticationHotel.api.models.operations.register;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserOutput implements OperationResponse {
    private UUID id;
}
