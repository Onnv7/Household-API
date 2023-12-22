package com.onnv.household.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.dto.request.ChangeNewPasswordRequest;
import com.onnv.household.dto.request.ForgetPasswordRequest;
import com.onnv.household.dto.request.RefreshTokenRequest;
import com.onnv.household.dto.request.RegisterUserAccountRequest;
import com.onnv.household.dto.response.RefreshTokenResponse;
import com.onnv.household.dto.response.UserLoginResponse;
import com.onnv.household.dto.response.UserRegisterResponse;
import com.onnv.household.entity.ConfirmationEntity;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.entity.UserTokenEntity;
import com.onnv.household.enums.ConfirmationStatus;
import com.onnv.household.enums.RefreshTokenStatus;
import com.onnv.household.enums.Roles;
import com.onnv.household.model.CustomException;
import com.onnv.household.model.NoRollBackException;
import com.onnv.household.security.custom.UserPrincipal;
import com.onnv.household.utils.EmailUtils;
import com.onnv.household.utils.JwtUtils;
import com.onnv.household.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Arrays;
import java.util.List;

import static com.onnv.household.utils.JwtUtils.ROLES_CLAIM_KEY;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    private final ModelMapperUtils modelMapperUtils;
    private final ConfirmationService confirmationService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final EmailUtils emailUtils;
    private final UserTokenService userTokenService;

    public UserLoginResponse userLogin(String email, String password) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (Exception e) {
            throw new CustomException(ErrorConstant.CREDENTIAL_INVALID);
        }
        var principalAuthenticated = (UserPrincipal) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();

        String userId = principalAuthenticated.getUserId();
        String username = principalAuthenticated.getUsername();
        var accessToken = jwtUtils.issueAccessToken(userId, username, roles);
        String refreshToken = jwtUtils.issueRefreshToken(userId, username, roles);

        userTokenService.createUserToken(userId, refreshToken);
        return UserLoginResponse.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
    @Transactional
    public UserRegisterResponse registerNewUser(RegisterUserAccountRequest data) throws RuntimeException {
        String email = data.getEmail();
        if(userService.isExistedEmail(email)) {
            throw new CustomException(ErrorConstant.EMAIL_EXISTED);
        }
        if(confirmationService.isVerifiedEmail(email)) {
            throw new CustomException(ErrorConstant.EMAIL_UNVERIFIED);
        }
        confirmationService.deleteConfirmationByEmail(email);

        data.setPassword(passwordEncoder.encode(data.getPassword()));
        UserEntity user = modelMapperUtils.mapNotNullClass(data, UserEntity.class);

        UserEntity userSaved = userService.saveUser(user);

        userRoleService.createRoleForUser(userSaved.getId(), Roles.ROLE_USER);

        var roles = Arrays.asList(Roles.ROLE_USER.name());
        String userId = userSaved.getId();
        String username = userSaved.getEmail();

        var accessToken = jwtUtils.issueAccessToken(userId, username, roles);
        String refreshToken = jwtUtils.issueRefreshToken(userId, username, roles);

        userTokenService.createUserToken(userId, refreshToken);

        return UserRegisterResponse.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }


    public void sendEmailToRegister(String email) {
        UserEntity user = userService.findUserByEmail(email);
        if(user != null) {
            throw new CustomException(ErrorConstant.EMAIL_EXISTED);
        }
        ConfirmationEntity confirmation = confirmationService.findConfirmationByEmail(email);
        if(confirmation != null) {
            confirmation = confirmationService.renewConfirmation(confirmation);
        } else {
            confirmation = confirmationService.createConfirmation(email);
        }
        emailUtils.sendHtmlVerifyCodeToRegister(email, confirmation.getToken());
    }

    public void verifyCodeEmail(String email, String code) {
        confirmationService.verifyEmailCode(email, code);
    }

    @Transactional(noRollbackFor = {NoRollBackException.class})
    public RefreshTokenResponse refreshToken(RefreshTokenRequest data) {

        DecodedJWT jwt = jwtUtils.decodeRefreshToken(data.getRefreshToken());
        String userId = jwt.getSubject().toString();

        UserEntity user = userService.getUserById(userId);

        UserTokenEntity userToken = userTokenService.getUserTokenByUserAndRefreshToken(user, data.getRefreshToken());
        if(userToken.getStatus() == RefreshTokenStatus.USED) {
            userTokenService.deleteAllUserTokenByUser(user);
            throw new NoRollBackException(ErrorConstant.STOLEN_TOKEN);
        }
        List<String> roles = jwt.getClaim(ROLES_CLAIM_KEY).asList(String.class);
        String newAccessToken = jwtUtils.issueAccessToken(user.getId(), user.getEmail(), roles);
        String newRefreshToken = jwtUtils.issueRefreshToken(user.getId(), user.getEmail(), roles);
        userTokenService.updateUserTokenToUsed(userToken);
        userTokenService.createUserToken(user.getId(), newRefreshToken);

        return  RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public void sendEmailCodeForForgetPassword(ForgetPasswordRequest data) {
        String email = data.getEmail();
        UserEntity user = userService.getUserByEmail(data.getEmail());

        ConfirmationEntity confirmation = confirmationService.findConfirmationByEmail(email);
        if(confirmation != null) {
            confirmation = confirmationService.renewConfirmation(confirmation);
        } else {
            confirmation = confirmationService.createConfirmation(email);
        }
        emailUtils.sendHtmlVerifyCode(email, confirmation.getToken(), user.getFirstName() + " " + user.getLastName());
    }

    @Transactional
    public void changeNewPassword(ChangeNewPasswordRequest data) {
        String email = data.getEmail();
        UserEntity user = userService.getUserByEmail(email);

        ConfirmationEntity confirmation = confirmationService.getConfirmationByEmail(email);
        if(confirmation.getStatus() == ConfirmationStatus.UNVERIFIED) {
            throw new CustomException(ErrorConstant.EMAIL_UNVERIFIED);
        } else {
            confirmationService.deleteConfirmationByEmail(email);
        }
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userService.updateUser(user);
    }
}
