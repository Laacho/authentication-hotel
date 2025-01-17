package com.tinqinacademy.authenticationHotel.api.models.exceptions.errorWrapper;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorWrapper {
    private List<Error> errors=new ArrayList<>();
    public void addErrors(Error error){
        this.errors.add(error);
    }
    public ResponseEntity<List<Error>> getErrors(){
        return ResponseEntity.ok(errors);
    }

}
