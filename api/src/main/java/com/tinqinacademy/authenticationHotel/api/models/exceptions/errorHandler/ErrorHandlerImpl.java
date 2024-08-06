package com.tinqinacademy.authenticationHotel.api.models.exceptions.errorHandler;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper.ErrorWrapper;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public ErrorWrapper handleError(Throwable t) {
//        if (t instanceof InvalidRoomByIdExceptions) {
//            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
//            errorWrapper.addErrors(
//                    Error.builder()
//                            .status(HttpStatus.NOT_FOUND)
//                            .statusCode(HttpStatus.NOT_FOUND.value())
//                            .message(t.getMessage())
//                            .build());
//            return errorWrapper;
//        }
//        if (t instanceof IllegalArgumentException) {
//            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
//            errorWrapper.addErrors(
//                    Error.builder()
//                            .status(HttpStatus.BAD_REQUEST)
//                            .statusCode(HttpStatus.BAD_REQUEST.value())
//                            .message(t.getMessage())
//                            .build());
//            return errorWrapper;
//        }
//        if (t instanceof InputDataException) {
//            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
//            errorWrapper.addErrors(
//                    Error.builder()
//                            .status(HttpStatus.BAD_REQUEST)
//                            .statusCode(HttpStatus.BAD_REQUEST.value())
//                            .message(t.getMessage())
//                            .build());
//            return errorWrapper;
//        }
//
//
       return null;

    }
}
