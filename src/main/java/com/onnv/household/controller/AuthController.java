package com.onnv.household.controller;

import com.onnv.household.constants.HttpConstant;
import com.onnv.household.constants.SuccessConstant;
import com.onnv.household.dto.request.*;
import com.onnv.household.dto.response.RefreshTokenResponse;
import com.onnv.household.dto.response.UserLoginResponse;
import com.onnv.household.dto.response.UserRegisterResponse;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.model.CustomException;
import com.onnv.household.model.ResponseAPI;
import com.onnv.household.service.AuthService;
import com.onnv.household.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onnv.household.constants.EndpointConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Tag(name = AUTH_TAG)
@RestController
@RequestMapping(AUTH_BASE_PATH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Operation(summary = AUTH_LOGIN_SUM)
    @PostMapping(AUTH_POST_USER_LOGIN_SUB_PATH)
    public ResponseEntity<ResponseAPI> userLogin(@RequestBody @Valid UserLoginRequest body) {
        UserLoginResponse resData = authService.userLogin(body.getEmail(), body.getPassword());
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.LOGIN_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = AUTH_REGISTER_SUM)
    @PostMapping(AUTH_POST_USER_REGISTER_SUB_PATH)
    public ResponseEntity<ResponseAPI> register(@RequestBody @Valid RegisterUserAccountRequest body) {
        UserRegisterResponse resData = authService.registerNewUser(body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.REGISTER_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @Operation(summary = AUTH_SEND_EMAIL_REGISTER_SUM)
    @PostMapping(AUTH_POST_SEND_EMAIL_REGISTER_SUB_PATH)
    public ResponseEntity<ResponseAPI> sendEmailToRegister(@RequestBody @Valid SendEmailRegisterRequest body) {
        authService.sendEmailToRegister(body.getEmail());
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.SEND_EMAIL_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = AUTH_VERIFY_EMAIL_CODE_SUM)
    @PostMapping(AUTH_POST_VERIFY_EMAIL_CODE_SUB_PATH)
    public ResponseEntity<ResponseAPI> verifyEmailCode(@RequestBody @Valid VerifyEmailCodeRequest body) {
        authService.verifyCodeEmail(body.getEmail(), body.getCode());
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.VERIFY_EMAIL_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = AUTH_REFRESH_USER_TOKEN_SUM)
    @PostMapping(AUTH_POST_REFRESH_USER_TOKEN_SUB_PATH)
    public ResponseEntity<ResponseAPI> refreshUserToken(@RequestBody @Valid RefreshTokenRequest body) {
        RefreshTokenResponse resData = authService.refreshToken(body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.REFRESH_TOKEN_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = AUTH_SEND_EMAIL_FORGOT_PASSWORD_SUM)
    @PostMapping(AUTH_POST_SEND_EMAIL_FORGOT_PASSWORD_SUB_PATH)
    public ResponseEntity<ResponseAPI> sendEmailCodeForForgetPassword(@RequestBody @Valid ForgetPasswordRequest body) {
        authService.sendEmailCodeForForgetPassword(body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.SEND_EMAIL_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = AUTH_CHANGE_NEW_PASSWORD_SUM)
    @PostMapping(AUTH_POST_CHANGE_NEW_PASSWORD_SUB_PATH)
    public ResponseEntity<ResponseAPI> changeNewPassword(@RequestBody @Valid ChangeNewPasswordRequest body) {
        authService.changeNewPassword(body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
