package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "product_image")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageEntity {
    @Id
//    @GenericGenerator(name = "image_id", strategy = TIME_ID_GENERATOR)
//    @GeneratedValue(generator = "image_id")
    private String id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isThumbnail;
    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private ProductEntity product;
}
