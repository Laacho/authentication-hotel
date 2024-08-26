package com.tinqinacademy.authenticationHotel.rest.config;

import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@Component
@RequestScope
public class UserContext {
    private User currUser;
    private String jwtToken;
}