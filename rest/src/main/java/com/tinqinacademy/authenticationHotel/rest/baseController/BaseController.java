package com.tinqinacademy.authenticationHotel.rest.baseController;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationOutput;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class BaseController {
    public ResponseEntity<?> handleResponse(Either<Error, ? extends OperationOutput> result) {
        if (result.isRight()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getLeft());

    }
}
