package com.tinqinacademy.authenticationHotel.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.tinqinacademy.authenticationHotel")
//@EnableJdbcRepositories(basePackages = "com.tinqinacademy.hotel.persistence.repository.interfaces")
//@EntityScan(basePackages = "com.tinqinacademy.authenticationHotel.persistence.entities")
public class AuthenticationHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationHotelApplication.class, args);
    }

}
