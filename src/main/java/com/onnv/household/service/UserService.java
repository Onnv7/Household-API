package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.dto.request.UpdateUserProfileByIdRequest;
import com.onnv.household.dto.response.GetUserProfileByIdResponse;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.enums.Roles;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.UserRepository;
import com.onnv.household.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final UserRepository userRepository;
    private final ModelMapperUtils modelMapperUtils;

    public void updateUser(UserEntity data) {
        userRepository.save(data);
    }
    public boolean isExistedEmail(String email) {
        UserEntity user = findUserByEmail(email);
        if(user != null) {
            return true;
        }
        return false;
    }
    public UserEntity getUserById(String id) throws RuntimeException {
        return userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorConstant.USER_NOT_FOUND));
    }

    public UserEntity findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }
    public UserEntity getUserByEmail(String email) throws RuntimeException {
        UserEntity user = userRepository.findByEmail(email);
        if(user == null) {
            throw new CustomException(ErrorConstant.USER_NOT_FOUND + " - " + email);
        }
        return user;
    }
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional
    public void createAdminUser(UserEntity admin) {
        UserEntity adminEntity = userRepository.save(admin);
        userRoleService.createRoleForUser(adminEntity.getId(), Roles.ROLE_ADMIN);
        userRoleService.createRoleForUser(adminEntity.getId(), Roles.ROLE_USER);
    }

    public GetUserProfileByIdResponse getUserProfileById(String userId) {
        UserEntity user = getUserById(userId);
        return modelMapperUtils.mapNotNullClass(user, GetUserProfileByIdResponse.class);
    }
    public void updateUserProfileById(String userId, UpdateUserProfileByIdRequest data) {
        UserEntity user = getUserById(userId);
        modelMapperUtils.map(data, user);
        userRepository.save(user);
    }

}
