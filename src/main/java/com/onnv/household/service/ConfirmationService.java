package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.entity.ConfirmationEntity;
import com.onnv.household.enums.ConfirmationStatus;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.ConfirmationRepository;
import com.onnv.household.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationService {
    private final ConfirmationRepository confirmationRepository;
    private final UserService userService;

    public boolean isExistedConfirmation(String email) {
        ConfirmationEntity entity = confirmationRepository.findByEmail(email);
        if (entity == null) {
            return false;
        }
        return true;
    }
    public ConfirmationEntity findConfirmationByEmail(String email) {
        ConfirmationEntity entity = confirmationRepository.findByEmail(email);
        return entity;
    }
    public ConfirmationEntity renewConfirmation(ConfirmationEntity data) {
        data.setToken(RandomUtils.generateRandomCode(6));
        data.setStatus(ConfirmationStatus.UNVERIFIED);
        return confirmationRepository.save(data);
    }
    public ConfirmationEntity getConfirmationByEmail(String email) {
        ConfirmationEntity entity = confirmationRepository.findByEmail(email);
        if (entity == null) {
            throw new CustomException(ErrorConstant.NOT_FOUND + " - " +email);
        }
        return entity;
    }
    public ConfirmationEntity createConfirmation(String email) {
        return confirmationRepository.save(
                ConfirmationEntity.builder()
                        .email(email)
                        .token(RandomUtils.generateRandomCode(6))
                        .status(ConfirmationStatus.UNVERIFIED)
                        .build()
        );
    }
    public void verifyEmailCode(String email, String code) {
        ConfirmationEntity entity = confirmationRepository.findByEmail(email);
        if (entity == null) {
            throw new CustomException(ErrorConstant.NOT_FOUND + " - " +email);
        }
        if(entity.getToken().equals(code)) {
            entity.setStatus(ConfirmationStatus.VERIFIED);
        } else {
            throw new CustomException(ErrorConstant.EMAIL_VERIFY_FAIL);
        }
        confirmationRepository.save(entity);
    }
    public boolean isVerifiedEmail(String email) {
        ConfirmationEntity entity = confirmationRepository.findByEmail(email);

        if(entity == null || entity.getStatus() == ConfirmationStatus.UNVERIFIED) {
            return true;
        }
        return false;
    }

    public void deleteConfirmationByEmail(String email) {
        confirmationRepository.deleteByEmail(email);
    }

}
