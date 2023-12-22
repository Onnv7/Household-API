package com.onnv.household.repository;

import com.onnv.household.entity.RoleEntity;
import com.onnv.household.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByRoleName(Roles role);
}
