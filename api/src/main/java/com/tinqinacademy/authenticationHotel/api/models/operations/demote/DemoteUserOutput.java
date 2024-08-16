package com.tinqinacademy.authenticationHotel.api.models.operations.demote;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoteUserOutput implements OperationResponse {
    private String message;
}
