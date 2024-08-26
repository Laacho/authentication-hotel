package com.tinqinacademy.authenticationHotel.core.service.converters;

import com.tinqinacademy.authenticationHotel.api.models.operations.register.RegisterUserInput;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.enums.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserInputToUserBuilder implements Converter<RegisterUserInput, User.UserBuilder> {

    @Override
    public User.UserBuilder convert(RegisterUserInput source) {
        return User.builder()
                .username(source.getUsername())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .birthdate(source.getBirthday())
                .role(Role.USER)
                .isConfirmed(false);
    }
}
