package com.onnv.household.repository;

import com.onnv.household.dto.response.GetProductNonHiddenByCategoryIdResponse;
import com.onnv.household.dto.response.GetProductNonHiddenResponse;
import com.onnv.household.entity.CategoryEntity;
import com.onnv.household.entity.ProductEntity;
import com.onnv.household.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByStatusNot(ProductStatus status);
    List<ProductEntity> findByCategoryIn(List<CategoryEntity> categories);
    ProductEntity findByIdAndStatusNot(String id, ProductStatus status);


    @Query(value = """
            SELECT p.id, p.name, p.price, pi.url as thumbnailUrl
            FROM product p
            JOIN product_image pi ON p.id = pi.product_id
            WHERE status != 'HIDDEN' and pi.is_thumbnail = true
            LIMIT ?2 OFFSET ?1
            """, nativeQuery = true)
    List<GetProductNonHiddenResponse> getProductNonHidden(int offset, int size);

    @Query(value = """
            SELECT p.id, p.name, p.price, pi.url as thumbnailUrl
            FROM product p
            JOIN product_image pi ON p.id = pi.product_id
            WHERE status != 'HIDDEN' and pi.is_thumbnail = true and p.category_id in ?1
            """, nativeQuery = true)
    List<GetProductNonHiddenByCategoryIdResponse> getProductListByCategoryIn(List<String> categoryIdList);
}
