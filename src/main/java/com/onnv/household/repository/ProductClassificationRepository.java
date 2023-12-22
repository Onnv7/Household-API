package com.onnv.household.repository;

import com.onnv.household.entity.ProductClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductClassificationRepository extends JpaRepository<ProductClassificationEntity, String> {
    @Query(value = """
            select pc.price
            from product p
            join product_classification pc on p.id = pc.product_id
            where pc.name = ?2 and p.id = ?1
            """, nativeQuery = true)
    Double getProductPriceByTypeName(String productId, String typeName);
}
