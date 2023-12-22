package com.onnv.household.service;

import com.onnv.household.entity.RoleEntity;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.entity.UserRoleEntity;
import com.onnv.household.entity.id.UserRoleId;
import com.onnv.household.enums.Roles;
import com.onnv.household.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    public final RoleService roleService;

    @Autowired
    @Lazy
    public UserService userService;

    public void createRoleForUser(String userId, Roles roleName) {
        UserEntity user = userService.getUserById(userId);
        RoleEntity role = roleService.findRoleByRoleName(roleName);
        UserRoleEntity userRole = UserRoleEntity.builder()
                .id(new UserRoleId(user.getId(), role.getId()))
                .role(role)
                .user(user)
                .build();
        userRoleRepository.save(userRole);
    }

}
