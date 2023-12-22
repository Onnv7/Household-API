package com.onnv.household.repository;

import com.onnv.household.entity.ConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, String> {

    ConfirmationEntity findByEmail(String email);
    void deleteByEmail(String email);
}
