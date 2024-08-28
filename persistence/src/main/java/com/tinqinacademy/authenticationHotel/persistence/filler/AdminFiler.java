package com.tinqinacademy.authenticationHotel.persistence.filler;

import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.enums.Role;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminFiler implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminFiler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<User> usersByRoleIs = userRepository.findUsersByRoleIs(Role.ADMIN);
        if (usersByRoleIs.isEmpty()) {
            User defaultAdmin = User.builder()
                    .isConfirmed(true)
                    .role(Role.ADMIN)
                    .firstName("admin")
                    .password(passwordEncoder.encode("123"))
                    .username("admin")
                    .email("lachezarradushev@gmail.com") //company private email
                    .build();
            userRepository.save(defaultAdmin);
        }

    }
}
