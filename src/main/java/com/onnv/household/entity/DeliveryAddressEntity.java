package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "delivery_address")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressEntity {

    @Id
    @GenericGenerator(name = "delivery_address_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "delivery_address_id")
    private String id;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String ward;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, name = "is_default", columnDefinition = "boolean default false")
    private boolean isDefault;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private UserEntity user;
}
