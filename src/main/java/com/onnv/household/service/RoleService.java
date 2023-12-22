package com.onnv.household.service;

import com.onnv.household.entity.RoleEntity;
import com.onnv.household.enums.Roles;
import com.onnv.household.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void createRole(RoleEntity role) {
        roleRepository.save(role);
    }

    public boolean isExistedRole(Roles role) {
        return roleRepository.findByRoleName(role) != null;
    }

    public RoleEntity findRoleByRoleName(Roles role) {
        return roleRepository.findByRoleName(role);
    }
}
