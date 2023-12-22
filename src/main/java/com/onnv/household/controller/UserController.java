package com.onnv.household.controller;

import com.onnv.household.constants.SuccessConstant;
import com.onnv.household.dto.GetUserResponseDtoXXXX;
import com.onnv.household.dto.request.CreateProductRequest;
import com.onnv.household.dto.request.RegisterUserAccountRequest;
import com.onnv.household.dto.request.UpdateUserProfileByIdRequest;
import com.onnv.household.dto.response.GetUserProfileByIdResponse;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.model.ResponseAPI;
import com.onnv.household.service.UserService;
import com.onnv.household.utils.ModelMapperUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.onnv.household.constants.BeanConstant.MODEL_MAPPER_NOT_NULL;
import static com.onnv.household.constants.EndpointConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Tag(name = USER_TAG)
@RestController
@RequestMapping(USER_BASE_PATH)
@RequiredArgsConstructor
public class UserController {
    private final ModelMapperUtils modelMapperUtils;
    @Autowired
    @Qualifier(MODEL_MAPPER_NOT_NULL)
    private ModelMapper modelMapperNotNull;
    @Autowired
    private UserService userService;


    @GetMapping(GET_USER_BY_EMAIL_SUB_PATH)
    public ResponseEntity<ResponseAPI>  getUser(@PathVariable("email") String email) {
        UserEntity data =  userService.findUserByEmail(email);
        System.out.println(data.getUserRoleList().get(0).getRole().getRoleName());
        GetUserResponseDtoXXXX rs = modelMapperNotNull.map(data, GetUserResponseDtoXXXX.class);
//        Hibernate.initialize(data.getUserRoleList());
        ResponseAPI res = ResponseAPI.builder()
                .message("Get user")
                .data(data)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = USER_GET_PROFILE_BY_ID)
    @PostMapping(path = GET_USER_PROFILE_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> getUserProfileById(@PathVariable(USER_ID) String userId) {
        GetUserProfileByIdResponse resData = userService.getUserProfileById(userId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = USER_UPDATE_PROFILE_BY_ID)
    @PutMapping(path = PUT_USER_UPDATE_PROFILE_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> updateUserProfileById(@PathVariable(USER_ID) String userId, @RequestBody @Valid UpdateUserProfileByIdRequest body) {
        userService.updateUserProfileById(userId, body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
