package com.onnv.household.constants;

import static com.onnv.household.constants.EndpointConstant.*;

public class SecurityConstant {
    public static final String[] GET_AUTH_WHITELIST = {
            "/kafka/**",
            "/api/test/**",
            "/api/payment/**",
            "/create_payment",
            "/IPN/**",
            "/openapi/**", "/v3/api-docs/**", "/openapi/swagger-config/**",
            "/v3/api-docs.yaml", "/swagger-ui/**", "/swagger-ui.html",
            "/api/order/test",
            GET_CATEGORY_CREATE_PATH,
            GET_USER_BY_EMAIL_PATH, GET_PRODUCT_NOT_HIDDEN_BY_ID_PATH, GET_PRODUCT_NOT_HIDDEN_BY_CATEGORY_PATH
    };
    public static final String[] POST_AUTH_WHITELIST = {
            AUTH_POST_USER_LOGIN_PATH, AUTH_POST_USER_REGISTER_PATH,
            GET_PRODUCT_NOT_HIDDEN_PATH, AUTH_POST_SEND_EMAIL_REGISTER_PATH,
            AUTH_POST_VERIFY_EMAIL_CODE_PATH, AUTH_POST_REFRESH_USER_TOKEN_PATH,
            AUTH_POST_SEND_EMAIL_FORGOT_PASSWORD_PATH, AUTH_POST_CHANGE_NEW_PASSWORD_PATH
    };
    // USER
    public static final String[] GET_USER_PATH = {
            GET_USER_PROFILE_BY_ID_PATH, PUT_USER_UPDATE_PROFILE_BY_ID_PATH,
            GET_ADDRESS_BY_USER_ID_PATH, GET_ORDER_BILL_DETAILS_BY_ID_PATH,
            GET_ORDER_BILL_ALL_BY_USER_ID_AND_STATUS_PATH
    };
    public static final String[] PUT_USER_PATH = {
            PUT_ADDRESS_UPDATE_BY_ID_PATH
    };
    public static final String[] DELETE_USER_PATH = {
            DELETE_ADDRESS_BY_ID_PATH
    };
    public static final String[] POST_USER_PATH = {
            POST_ADDRESS_CREATE_PATH, POST_ORDER_BILL_CREATE_PATH
    };
    // ADMIN
    public static final String[] GET_ADMIN_PATH = {
            GET_ORDER_BILL_ALL_BY_STATUS_PATH
    };
    public static final String[] DELETE_ADMIN_PATH = {
            DELETE_PRODUCT_BY_ID_PATH, DELETE_CATEGORY_BY_ID_PATH
    };
    public static final String[] PUT_ADMIN_PATH = {
            PUT_PRODUCT_UPDATE_BY_ID_PATH,
            PUT_CATEGORY_UPDATE_BY_ID_PATH
    };
    public static final String[] PATCH_ADMIN_PATH = {
            PATCH_ORDER_BILL_ACCEPT_BY_ID_PATH, PATCH_ORDER_BILL_REFUSE_BY_ID_PATH,
            PATCH_ORDER_BILL_SUCCEED_BY_ID_PATH
    };
    public static final String[] POST_ADMIN_PATH = {
            POST_PRODUCT_CREATE_PATH, POST_CATEGORY_CREATE_PATH,
            POST_ORDER_BILL_ADD_STATUS_PATH
    };

}
