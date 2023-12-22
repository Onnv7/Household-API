package com.onnv.household.constants;

public class EndpointConstant {
    public static final String PREFIX_PATH = "/api";
    public static final String USER_BASE_PATH = PREFIX_PATH + "/user";
    public static final String PRODUCT_BASE_PATH = PREFIX_PATH + "/product";
    public static final String CATEGORY_BASE_PATH = PREFIX_PATH + "/category";
    public static final String ORDER_BILL_PATH = PREFIX_PATH + "/order";
    public static final String DELIVERY_ADDRESS_BASE_PATH = PREFIX_PATH + "/address";
    public static final String AUTH_BASE_PATH = PREFIX_PATH + "/auth";

    public static final String CATEGORY_ID_PATH = "/{categoryId}";
    public static final String CATEGORY_ID = "categoryId";
    public static final String PRODUCT_ID_PATH = "/{productId}";
    public static final String PRODUCT_ID = "productId";
    public static final String USER_ID_PATH = "/{userId}";
    public static final String USER_ID = "userId";
    public static final String ADDRESS_ID_PATH = "/{addressId}";
    public static final String ADDRESS_ID = "addressId";
    public static final String ORDER_ID_PATH = "/{orderId}";
    public static final String ORDER_ID = "orderId";


    public static final String PAGE_KEY = "page";
    public static final String SIZE_KEY = "size";
    public static final String ORDER_STATUS_KEY = "status";
    // AUTH =================================================================
    public static final String AUTH_POST_USER_LOGIN_SUB_PATH = "/login";
    public static final String AUTH_POST_USER_LOGIN_PATH = AUTH_BASE_PATH + AUTH_POST_USER_LOGIN_SUB_PATH;
    public static final String AUTH_POST_USER_REGISTER_SUB_PATH = "/register";
    public static final String AUTH_POST_USER_REGISTER_PATH = AUTH_BASE_PATH + AUTH_POST_USER_REGISTER_SUB_PATH;
    public static final String AUTH_POST_SEND_EMAIL_REGISTER_SUB_PATH = "/register/send-email";
    public static final String AUTH_POST_SEND_EMAIL_REGISTER_PATH = AUTH_BASE_PATH + AUTH_POST_SEND_EMAIL_REGISTER_SUB_PATH;
    public static final String AUTH_POST_VERIFY_EMAIL_CODE_SUB_PATH = "/code/verify";
    public static final String AUTH_POST_VERIFY_EMAIL_CODE_PATH = AUTH_BASE_PATH + AUTH_POST_VERIFY_EMAIL_CODE_SUB_PATH;
    public static final String AUTH_POST_REFRESH_USER_TOKEN_SUB_PATH = "/refresh-token";
    public static final String AUTH_POST_REFRESH_USER_TOKEN_PATH = AUTH_BASE_PATH + AUTH_POST_REFRESH_USER_TOKEN_SUB_PATH;
    public static final String AUTH_POST_SEND_EMAIL_FORGOT_PASSWORD_SUB_PATH = "/forgot-password/send-email";
    public static final String AUTH_POST_SEND_EMAIL_FORGOT_PASSWORD_PATH = AUTH_BASE_PATH + AUTH_POST_SEND_EMAIL_FORGOT_PASSWORD_SUB_PATH;
    public static final String AUTH_POST_CHANGE_NEW_PASSWORD_SUB_PATH = "/change-password";
    public static final String AUTH_POST_CHANGE_NEW_PASSWORD_PATH = AUTH_BASE_PATH + AUTH_POST_CHANGE_NEW_PASSWORD_SUB_PATH;

    // USER =================================================================
    public static final String GET_USER_BY_EMAIL_SUB_PATH = "/{email}";
    public static final String GET_USER_BY_EMAIL_PATH = USER_BASE_PATH + GET_USER_BY_EMAIL_SUB_PATH;

    // PRODUCT =================================================================
    public static final String POST_PRODUCT_CREATE_SUB_PATH = "/create";
    public static final String POST_PRODUCT_CREATE_PATH = PRODUCT_BASE_PATH + POST_PRODUCT_CREATE_SUB_PATH;
    public static final String GET_PRODUCT_NOT_HIDDEN_SUB_PATH = "";
    public static final String GET_PRODUCT_NOT_HIDDEN_PATH = PRODUCT_BASE_PATH + GET_PRODUCT_NOT_HIDDEN_SUB_PATH;
    public static final String GET_PRODUCT_NOT_HIDDEN_BY_CATEGORY_SUB_PATH = "/category" + CATEGORY_ID_PATH;
    public static final String GET_PRODUCT_NOT_HIDDEN_BY_CATEGORY_PATH = PRODUCT_BASE_PATH + GET_PRODUCT_NOT_HIDDEN_BY_CATEGORY_SUB_PATH;
    public static final String GET_PRODUCT_NOT_HIDDEN_BY_ID_SUB_PATH = PRODUCT_ID_PATH;
    public static final String GET_PRODUCT_NOT_HIDDEN_BY_ID_PATH = PRODUCT_BASE_PATH + GET_PRODUCT_NOT_HIDDEN_BY_ID_SUB_PATH;
    public static final String DELETE_PRODUCT_BY_ID_SUB_PATH = PRODUCT_ID_PATH;
    public static final String DELETE_PRODUCT_BY_ID_PATH = PRODUCT_BASE_PATH + DELETE_PRODUCT_BY_ID_SUB_PATH;
    public static final String PUT_PRODUCT_UPDATE_BY_ID_SUB_PATH = PRODUCT_ID_PATH;
    public static final String PUT_PRODUCT_UPDATE_BY_ID_PATH = PRODUCT_BASE_PATH + PUT_PRODUCT_UPDATE_BY_ID_SUB_PATH;

    // CATEGORY =================================================================
    public static final String POST_CATEGORY_CREATE_SUB_PATH = "/create";
    public static final String POST_CATEGORY_CREATE_PATH = CATEGORY_BASE_PATH + POST_CATEGORY_CREATE_SUB_PATH;
    public static final String GET_CATEGORY_VISIBLE_SUB_PATH = "";
    public static final String GET_CATEGORY_CREATE_PATH = CATEGORY_BASE_PATH + GET_CATEGORY_VISIBLE_SUB_PATH;
    public static final String PUT_CATEGORY_UPDATE_BY_ID_SUB_PATH = CATEGORY_ID_PATH;
    public static final String PUT_CATEGORY_UPDATE_BY_ID_PATH = CATEGORY_BASE_PATH + PUT_CATEGORY_UPDATE_BY_ID_SUB_PATH;
    public static final String DELETE_CATEGORY_BY_ID_SUB_PATH = CATEGORY_ID_PATH;
    public static final String DELETE_CATEGORY_BY_ID_PATH = CATEGORY_BASE_PATH + DELETE_CATEGORY_BY_ID_SUB_PATH;

    // USER =================================================================

    public static final String GET_USER_PROFILE_BY_ID_SUB_PATH = USER_ID_PATH;
    public static final String GET_USER_PROFILE_BY_ID_PATH = USER_BASE_PATH + GET_USER_PROFILE_BY_ID_SUB_PATH;
    public static final String PUT_USER_UPDATE_PROFILE_BY_ID_SUB_PATH = USER_ID_PATH;
    public static final String PUT_USER_UPDATE_PROFILE_BY_ID_PATH = USER_BASE_PATH + PUT_USER_UPDATE_PROFILE_BY_ID_SUB_PATH;

    // ADDRESS =================================================================

    public static final String POST_ADDRESS_CREATE_SUB_PATH = "/user" + USER_ID_PATH;
    public static final String POST_ADDRESS_CREATE_PATH = DELIVERY_ADDRESS_BASE_PATH + POST_ADDRESS_CREATE_SUB_PATH;
    public static final String GET_ADDRESS_BY_USER_ID_SUB_PATH = "/user" + USER_ID_PATH;
    public static final String GET_ADDRESS_BY_USER_ID_PATH = DELIVERY_ADDRESS_BASE_PATH + GET_ADDRESS_BY_USER_ID_SUB_PATH;
    public static final String PUT_ADDRESS_UPDATE_BY_ID_SUB_PATH = ADDRESS_ID_PATH ;
    public static final String PUT_ADDRESS_UPDATE_BY_ID_PATH = DELIVERY_ADDRESS_BASE_PATH + PUT_ADDRESS_UPDATE_BY_ID_SUB_PATH;
    public static final String DELETE_ADDRESS_BY_ID_SUB_PATH = ADDRESS_ID_PATH ;
    public static final String DELETE_ADDRESS_BY_ID_PATH = DELIVERY_ADDRESS_BASE_PATH + DELETE_ADDRESS_BY_ID_SUB_PATH;

    // ORDER BILL =================================================================

    public static final String POST_ORDER_BILL_CREATE_SUB_PATH = "/user" + USER_ID_PATH;
    public static final String POST_ORDER_BILL_CREATE_PATH = ORDER_BILL_PATH + POST_ORDER_BILL_CREATE_SUB_PATH;
    public static final String POST_ORDER_BILL_ADD_STATUS_SUB_PATH =  ORDER_ID_PATH;
    public static final String POST_ORDER_BILL_ADD_STATUS_PATH = ORDER_BILL_PATH + POST_ORDER_BILL_ADD_STATUS_SUB_PATH;
    public static final String GET_ORDER_BILL_DETAILS_BY_ID_SUB_PATH =  ORDER_ID_PATH;
    public static final String GET_ORDER_BILL_DETAILS_BY_ID_PATH = ORDER_BILL_PATH + GET_ORDER_BILL_DETAILS_BY_ID_SUB_PATH;
    public static final String GET_ORDER_BILL_ALL_BY_STATUS_SUB_PATH =  "/all";
    public static final String GET_ORDER_BILL_ALL_BY_STATUS_PATH = ORDER_BILL_PATH + GET_ORDER_BILL_ALL_BY_STATUS_SUB_PATH;
    public static final String PATCH_ORDER_BILL_ACCEPT_BY_ID_SUB_PATH =  ORDER_ID_PATH + "/accept";
    public static final String PATCH_ORDER_BILL_ACCEPT_BY_ID_PATH = ORDER_BILL_PATH + PATCH_ORDER_BILL_ACCEPT_BY_ID_SUB_PATH;
    public static final String PATCH_ORDER_BILL_REFUSE_BY_ID_SUB_PATH =  ORDER_ID_PATH + "/refuse";
    public static final String PATCH_ORDER_BILL_REFUSE_BY_ID_PATH = ORDER_BILL_PATH + PATCH_ORDER_BILL_REFUSE_BY_ID_SUB_PATH;
    public static final String PATCH_ORDER_BILL_SUCCEED_BY_ID_SUB_PATH =  ORDER_ID_PATH + "/succeed";
    public static final String PATCH_ORDER_BILL_SUCCEED_BY_ID_PATH = ORDER_BILL_PATH + PATCH_ORDER_BILL_SUCCEED_BY_ID_SUB_PATH;
    public static final String GET_ORDER_BILL_ALL_BY_USER_ID_AND_STATUS_SUB_PATH =  USER_ID_PATH + "/all";
    public static final String GET_ORDER_BILL_ALL_BY_USER_ID_AND_STATUS_PATH = ORDER_BILL_PATH + GET_ORDER_BILL_ALL_BY_USER_ID_AND_STATUS_SUB_PATH;
}
