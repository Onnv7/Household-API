package com.onnv.household.repository;

import com.onnv.household.entity.CategoryEntity;
import com.onnv.household.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    List<CategoryEntity> findCategoryByStatus(CategoryStatus status);
    List<CategoryEntity> findByStatusAndParentCategoryIsNull(CategoryStatus status);

}
