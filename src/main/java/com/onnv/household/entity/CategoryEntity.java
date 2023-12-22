package com.onnv.household.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onnv.household.enums.CategoryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

import static com.onnv.household.constants.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "category")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GenericGenerator(name = "category_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "category_id")
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @ManyToOne()
    @JoinColumn(name = "parent_category_id")
    @JsonManagedReference
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonBackReference
    private List<CategoryEntity> childCategoryList;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<ProductEntity> productList;


    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updateAt;

    @PreRemove
    public void preRemove() {
        getChildCategoryList().stream()
                .forEach(it -> {
                    it.setParentCategory(null);
                });
    }
}
