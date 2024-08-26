package com.tinqinacademy.authenticationHotel.api.models.operations.promote;

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
public class PromoteUserInput implements OperationRequest {
    @NotNull(message = "userId cannot be null")
    private UUID userId;
}
