package com.tinqinacademy.authenticationHotel.core.service.utils;

import com.tinqinacademy.authenticationHotel.persistence.repository.BlacklistedCodesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Generator {
    private final BlacklistedCodesRepository blacklistedCodesRepository;


    public String generateRandomCode() {
        String availableCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int lengthOfTheCode=10;
        String result;
        do {
            result = RandomStringUtils.random(lengthOfTheCode, availableCharacters);
        }
        while (blacklistedCodesRepository.existsByCode(result));
        return result;
    }

    public String generatePasswordRecoveryCode() {
        String availableCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8;
        return RandomStringUtils.random(length, availableCharacters);
    }
}
