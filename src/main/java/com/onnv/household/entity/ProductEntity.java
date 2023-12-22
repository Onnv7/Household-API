package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onnv.household.enums.ProductStatus;
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
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity  {
    @Id
    @GenericGenerator(name = "product_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "product_id")
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, columnDefinition="TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private CategoryEntity category;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updateAt;

    @OneToMany(mappedBy = "product", cascade = { CascadeType.REMOVE, CascadeType.PERSIST }) //, cascade = CascadeType.ALL, fetch = FetchType.EAGER
    @JsonBackReference
    private List<ProductClassificationEntity> productClassificationList = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JsonBackReference
    private List<ProductImageEntity> productImageList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<ItemOrderEntity> itemOrderList = new ArrayList<>();
}
