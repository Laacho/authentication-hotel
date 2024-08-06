package com.tinqinacademy.authenticationHotel.api.models.exceptions.baseError;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Error {
    private HttpStatus status;
    private Integer statusCode;
    private String message;
}
