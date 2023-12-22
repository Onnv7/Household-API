package com.onnv.household.repository;

import com.onnv.household.entity.ItemOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrderEntity, String> {
}
