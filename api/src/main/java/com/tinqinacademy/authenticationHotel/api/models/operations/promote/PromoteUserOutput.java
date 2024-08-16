package com.tinqinacademy.authenticationHotel.api.models.operations.promote;


import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoteUserOutput implements OperationResponse {
    private String message;
}
