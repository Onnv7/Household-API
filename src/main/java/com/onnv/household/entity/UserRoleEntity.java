package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onnv.household.entity.id.UserRoleId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity implements Serializable {
    @EmbeddedId
    private UserRoleId id;
    @ManyToOne()
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    @JsonManagedReference
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "role_id", insertable=false, updatable=false)
    private RoleEntity role ;
}
