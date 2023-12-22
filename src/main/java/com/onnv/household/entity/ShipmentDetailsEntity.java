package com.onnv.household.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;
@Entity
@Table(name = "shipment_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDetailsEntity {
    @Id
    @GenericGenerator(name = "shipment_details_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "shipment_details_id")
    private String id;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column()
    private String note;

    @OneToOne(mappedBy = "shipmentDetails")
    private OrderBillEntity orderBill;
}
