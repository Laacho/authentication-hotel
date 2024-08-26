package com.tinqinacademy.authenticationHotel.persistence.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    UNKNOWN("unknown");

    private final String code;

    Role(String role) {
        this.code = role;
    }

    @JsonCreator
    public static Role getByCode(String code) {
      return   Arrays.stream(Role.values())
              .filter(role -> role.code.equals(code))
              .findFirst()
              .orElse(UNKNOWN);
    }
    @JsonValue
    public String toString(){
        return code;
    }
}
