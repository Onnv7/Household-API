package com.onnv.household.entity;

import com.onnv.household.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "role")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GenericGenerator(name = "role_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "role_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = true)
    private Roles roleName;

    @OneToMany(mappedBy = "role")
    private List<UserRoleEntity> userRoleList;
}
