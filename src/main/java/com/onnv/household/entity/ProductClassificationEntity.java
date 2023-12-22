package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "product_classification")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClassificationEntity {
    @Id
    @GenericGenerator(name = "product_classification_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "product_classification_id")
    private String id;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    @JsonManagedReference
    private ProductEntity product;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;
}
