package com.tinqinacademy.authenticationHotel.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.tinqinacademy.authenticationHotel.persistence.repository")
@EntityScan(basePackages = "com.tinqinacademy.authenticationHotel.persistence.entities")
@ComponentScan(basePackages = "com.tinqinacademy.authenticationHotel")
public class AuthenticationHotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationHotelApplication.class, args);
    }

}
