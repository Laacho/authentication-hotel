package com.tinqinacademy.authenticationHotel.restExport;

import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTOutput;
import feign.Headers;
import feign.RequestLine;


@Headers({"Content-Type: application/json"})
public interface RestExportAuthentication {

    @RequestLine("POST /auth/validateJWT")
    ValidateJWTOutput isJwtValid(ValidateJWTInput input);
}

