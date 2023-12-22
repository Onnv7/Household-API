package com.onnv.household.command;

import com.onnv.household.entity.RoleEntity;
import com.onnv.household.enums.Roles;
import com.onnv.household.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class CreateRoleDataCommand implements CommandLineRunner {
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        try {
            boolean existedAdmin = roleService.isExistedRole(Roles.ROLE_ADMIN);
            boolean existedUser = roleService.isExistedRole(Roles.ROLE_USER);
            RoleEntity admin = RoleEntity.builder().roleName(Roles.ROLE_ADMIN).build();
            RoleEntity user = RoleEntity.builder().roleName(Roles.ROLE_USER).build();
            if(!existedAdmin) {
                roleService.createRole(admin);
                log.info("Admin role is created");
            } else {
                log.info("Admin role were existed");
            }
            if(!existedUser) {
                roleService.createRole(user);
                log.info("User role is created");
            } else {
                log.info("User role were existed");
            }
        }
        catch (Exception e) {
            log.error("Create role command failed: {}", e.getMessage());
        }
    }
}
