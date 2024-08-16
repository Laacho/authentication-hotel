package com.tinqinacademy.authenticationHotel.api.models.operations.logout;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutOutput implements OperationResponse {
    private String message;
}
