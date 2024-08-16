package com.tinqinacademy.authenticationHotel.api.models.operations.promote;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoteUserInput implements OperationRequest {
    private UUID userId;
}
