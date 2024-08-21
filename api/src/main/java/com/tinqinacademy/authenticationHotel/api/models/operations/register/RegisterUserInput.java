package com.tinqinacademy.authenticationHotel.api.models.operations.register;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserInput implements OperationRequest {
    @Size(min = 1, max = 50)
    @NotNull(message = "Username cannot be null")
    private String username;
    @Size(min = 1, max = 50)
    @NotNull(message = "Password cannot be null")
    private String password;
    @Size(min = 1, max = 50)
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @Size(min = 1, max = 50)
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @Size(min = 1)
    @NotNull(message = "Email cannot be null")
    private String email;
    @Past(message = "must be a past date")
    @NotNull(message = "cannot be null")
    private LocalDate birthday;



}
