package com.tinqinacademy.authenticationHotel.api.models.operations.logout;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutInput implements OperationRequest {

    private String jwt;
    private String userContextId;
}
