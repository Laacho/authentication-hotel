package com.tinqinacademy.authenticationHotel.api.models.operations.demote;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoteUserInput implements OperationRequest {
    @NotBlank(message = "userID cannot be blank")
    private UUID userId;
}
