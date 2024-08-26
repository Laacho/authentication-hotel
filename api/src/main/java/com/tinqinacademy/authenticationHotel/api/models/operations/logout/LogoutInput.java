package com.tinqinacademy.authenticationHotel.api.models.operations.logout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LogoutInput implements OperationRequest {

    @JsonIgnore
    private String jwt;

    @JsonIgnore
    private String userContextId;
}
