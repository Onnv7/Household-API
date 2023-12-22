package com.onnv.household.entity;

import com.onnv.household.enums.ItemOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "item_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderEntity {
    @Id
    @GenericGenerator(name = "item_order_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "item_order_id")
    private String id;

    @Column(name = "type_name")
    private String typeName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column()
    private ItemOrderStatus status;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column()
    private String note;

    @ManyToOne()
    @JoinColumn(name = "order_bill_id", nullable = false)
    private OrderBillEntity orderBill;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updateAt;
}
