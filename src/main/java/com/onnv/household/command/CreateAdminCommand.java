package com.onnv.household.command;


import com.onnv.household.entity.UserEntity;
import com.onnv.household.enums.Gender;
import com.onnv.household.enums.Roles;
import com.onnv.household.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class CreateAdminCommand implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        UserEntity existedAdmin = userService.findUserByEmail("nva611@gmail.com");
        if(existedAdmin != null) {
            log.info("ADMIN IS EXISTED");
            return;
        }
        UserEntity admin = UserEntity.builder()
                .email("nva611@gmail.com")
                .password("$2a$07$8uAcnHtjJyuBjFq8c73jKuuKj/KbxqT79v7.fpVtzYUUUYvUmzbXG")
                .firstName("An")
                .lastName("Nguyen")
                .gender(Gender.MALE)
                .birthDate(new Date(2002, 10, 6))
                .build();
        userService.createAdminUser(admin);
        log.info("ADMIN IS CREATED");
    }
}
