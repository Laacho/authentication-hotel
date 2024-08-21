package com.tinqinacademy.authenticationHotel.api.models.operations.promote;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoteUserInput implements OperationRequest {
    @Size(min = 1)
    @NotNull(message = "userId cannot be null")
    private UUID userId;
}
