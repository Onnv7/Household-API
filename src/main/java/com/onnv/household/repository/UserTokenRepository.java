package com.onnv.household.repository;

import com.onnv.household.entity.UserEntity;
import com.onnv.household.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, String> {
    UserTokenEntity findByUserAndRefreshToken(UserEntity user, String refreshToken);
    void deleteByUser(UserEntity user);
    void deleteByUserId(String userId);
}
