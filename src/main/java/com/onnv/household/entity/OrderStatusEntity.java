package com.onnv.household.entity;

import com.onnv.household.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "order_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusEntity {
    @Id
    @GenericGenerator(name = "order_status_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "order_status_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column()
    private String note;

    @ManyToOne()
    @JoinColumn(name = "order_bill_id", nullable = false)
    private OrderBillEntity orderBill;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;


}
