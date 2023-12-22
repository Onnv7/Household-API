package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "order_bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBillEntity {
    @Id
    @GenericGenerator(name = "order_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "order_id")
    private String id;

    @Column()
    private String note;

    @Column(name = "total_product_price", nullable = false)
    private Double totalProductPrice;

    @Column(name = "total_bill")
    private Double totalBill;

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updateAt;

    @OneToOne()
    @JoinColumn(name = "shipment_details_id", nullable = false)
    private ShipmentDetailsEntity shipmentDetails;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

    @OneToMany(mappedBy = "orderBill", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderStatusEntity> orderStatusList = new ArrayList<>();

    @OneToMany(mappedBy = "orderBill", cascade = {CascadeType.PERSIST})
    private List<ItemOrderEntity> itemOrderList = new ArrayList<>();
}
