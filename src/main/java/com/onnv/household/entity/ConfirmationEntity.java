package com.onnv.household.entity;

import com.onnv.household.enums.ConfirmationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "confirmation")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationEntity {

    @Id
    @GenericGenerator(name = "confirmation_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "confirmation_id")
    private String id;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfirmationStatus status;
}
