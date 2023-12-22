package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.onnv.household.enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @GenericGenerator(name = "user_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "user_id")
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updateAt;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<UserRoleEntity> userRoleList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<UserTokenEntity> userTokenList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<DeliveryAddressEntity> deliveryAddressList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<OrderBillEntity> orderList = new ArrayList<>();

}
