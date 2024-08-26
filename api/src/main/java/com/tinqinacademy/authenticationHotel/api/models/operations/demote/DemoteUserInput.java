package com.tinqinacademy.authenticationHotel.api.models.operations.demote;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoteUserInput implements OperationRequest {
    @NotNull(message = "userID cannot be null")
    private UUID userId;
}
