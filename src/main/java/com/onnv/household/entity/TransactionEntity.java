package com.onnv.household.entity;

import com.onnv.household.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GenericGenerator(name = "transaction_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "transaction_id")
    private String id;

    @Column(name = "payment_total", nullable = false)
    private Double paymentTotal;

    @Column(columnDefinition = "DOUBLE DEFAULT 0.0", nullable = false)
    private Double paid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status = TransactionStatus.UNPAID;

    @OneToOne(mappedBy = "transaction")
    private OrderBillEntity orderBill;
}
