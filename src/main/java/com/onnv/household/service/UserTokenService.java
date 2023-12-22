package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.entity.UserTokenEntity;
import com.onnv.household.enums.RefreshTokenStatus;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserTokenService {
    private final UserService userService;
    private final UserTokenRepository userTokenRepository;

    void createUserToken(String userId, String refreshToken) {
        UserEntity user = userService.getUserById(userId);

        UserTokenEntity userToken = UserTokenEntity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .status(RefreshTokenStatus.AVAILABLE)
                .build();
        userTokenRepository.save(userToken);
    }

    UserTokenEntity getUserTokenByUserAndRefreshToken(UserEntity user, String refreshToken) {
        UserTokenEntity userToken = userTokenRepository.findByUserAndRefreshToken(user, refreshToken);
        if(userToken == null) {
            throw new CustomException(ErrorConstant.NOT_FOUND + " refresh token");
        }
        return userToken;
    }

    void deleteAllUserTokenByUser(UserEntity user) {
        userTokenRepository.deleteByUserId(user.getId());
    }
    void updateUserTokenToUsed(UserTokenEntity data) {
        data.setStatus(RefreshTokenStatus.USED);
        userTokenRepository.save(data);
    }
}
