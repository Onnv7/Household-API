package com.onnv.household.repository;

import com.onnv.household.entity.ShipmentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentDetailsRepository extends JpaRepository<ShipmentDetailsEntity, String> {
}
