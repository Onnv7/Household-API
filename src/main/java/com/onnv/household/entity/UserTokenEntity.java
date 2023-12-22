package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onnv.household.enums.RefreshTokenStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "user_token")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenEntity {

    @Id
    @GenericGenerator(name = "user_token_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "user_token_id")
    private String id;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RefreshTokenStatus status;

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
