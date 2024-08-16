package com.tinqinacademy.authenticationHotel.api.models.operations.demote;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoteUserInput implements OperationRequest {
    private UUID userId;
}
