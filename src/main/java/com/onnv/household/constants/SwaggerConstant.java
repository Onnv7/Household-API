package com.onnv.household.constants;

public class SwaggerConstant {
    // Tag =========================================================

    public static final String PHONE_NUMBER_REGEX = "^\\d{10}$";
    public static final String ADMIN = " [ADMIN] ";
    public static final String USER = " [USER] ";
    public static final String FREE = " [FREE] ";
    public static final String PRODUCT_TAG = "PRODUCT";
    public static final String CATEGORY_TAG = "CATEGORY";
    public static final String DELIVERY_ADDRESS_TAG = "DELIVERY ADDRESS";
    public static final String ORDER_BILL_TAG = "ORDER BILL";
    public static final String AUTH_TAG = "AUTH";
    public static final String USER_TAG = "USER";
    // configuration parameters =================================================================
    public static final String JSON_MEDIA_TYPE = "application/json";
    public static final String FORM_DATA_MEDIA_TYPE = "multipart/form-data";
    // example values =================================================================
    public static final String EMAIL_EX = "nva611@gmail.com";
    public static final String PASSWORD_EX = "112233";
    public static final String CODE_EX = "123456";
    public static final String FIRST_NAME_EX = "An";
    public static final String LAST_NAME_EX = "Nguyen";
    public static final String GENDER_EX = "FEMALE";
    public static final String BIRTH_DATE_EX = "2002-11-11";
    public static final String PRODUCT_NAME_EX = "May loc nuoc";
    public static final String REFRESH_TOKEN_EX = "132465789";
    public static final String PRODUCT_PRICE_EX = "1000";
    public static final String PRODUCT_STATUS_EX = "IN_STOCK";
    public static final String PRODUCT_DESCRIPTION_EX = "Mo ta san pham";
    public static final String CATEGORY_STATUS_EX = "VISIBLE";
    public static final String ID_EX = "174131651";
    public static final String CATEGORY_NAME_EX = "Do dien tu";
    public static final String PRODUCT_CLASSIFICATION_PRICE_EX = "1000";
    public static final String PROVINCE_EX = "Binh Duong";
    public static final String DISTRICT_EX = "Di An";
    public static final String WARD_EX = "Tan Binh";
    public static final String ADDRESS_DETAILS_EX = "Nguyen Thi Tuoi, Tan Hiep";
    public static final String FULL_ADDRESS_EX = "Nguyen Thi Tuoi, Tan Hiep - Tan Binh - Di An - Binh Duong";
    public static final String PHONE_NUMBER_EX = "0336630201";
    public static final String SHIPPING_NOTE_EX = "Giao vao buoi sang";
    public static final String ITEM_NOTE_EX = "Lay cai moi";
    public static final String ORDER_BILL_NOTE_EX = "Lam le nhe";
    public static final String QUANTITY_EX = "2";
    public static final String TYPE_NAME_EX = "type1";
    public static final String ORDER_STATUS_EX = "PROCESSING";
    public static final String CANCELLATION_REASON_EX = "So luong khong du";
    public static final String  NOTE_EX = "Nothing";

    // AUTH =================================================================
    public static final String AUTH_LOGIN_SUM = FREE + "User login and get token";
    public static final String AUTH_REGISTER_SUM = FREE + "User login and get token";
    public static final String AUTH_SEND_EMAIL_REGISTER_SUM = FREE + "Send code to email for registration";
    public static final String AUTH_VERIFY_EMAIL_CODE_SUM = FREE + "Verify email code";
    public static final String AUTH_REFRESH_USER_TOKEN_SUM = FREE + "Refresh user token";
    public static final String AUTH_SEND_EMAIL_FORGOT_PASSWORD_SUM = FREE + "Send code to email to verify for forgot password";
    public static final String AUTH_CHANGE_NEW_PASSWORD_SUM = FREE + "Change new password";

    // CATEGORY =================================================================
    public static final String CATEGORY_CREATE_SUM = ADMIN + "Create a new category";
    public static final String CATEGORY_GET_VISIBLE_SUM = FREE + "Get categories which are visible";
    public static final String CATEGORY_UPDATE_BY_ID_SUM = ADMIN +  "Update category by id";
    public static final String CATEGORY_DELETE_BY_ID_SUM = ADMIN + "Delete category by id";

    // PRODUCT =================================================================
    public static final String PRODUCT_CREATE_SUM = ADMIN + "Create a new product" ;
    public static final String PRODUCT_GET_NOT_HIDDEN_SUM = FREE + "Get product which have not been hidden" ;
    public static final String PRODUCT_GET_NOT_HIDDEN_BY_ID_SUM = FREE + "Get product which have not been hidden by id" ;
    public static final String PRODUCT_DELETE_BY_ID_SUM = ADMIN + "Delete product by id" ;
    public static final String PRODUCT_GET_NOT_HIDDEN_BY_CATEGORY_SUM = FREE + "Get product which have not been hidden by category id";
    public static final String PRODUCT_UPDATE_BY_ID_SUM = ADMIN + "Update product by id";

    // USER =================================================================
    public static final String USER_GET_PROFILE_BY_ID = USER + "Get user's profile by id";
    public static final String USER_UPDATE_PROFILE_BY_ID = USER + "Update user's profile by id";

    // ADDRESS =================================================================
    public static final String ADDRESS_CREATE_SUM = USER + "Create a new address for user";
    public static final String ADDRESS_GET_BY_USER_ID_SUM = USER + "Get all addresses by user id";
    public static final String ADDRESS_UPDATE_BY_USER_ID_SUM = USER + "Update address by id";
    public static final String ADDRESS_DELETE_BY_USER_ID_SUM = USER + "Delete address by id";

    // ORDER =================================================================
    public static final String ORDER_CREATE_SUM = USER + "Create a new order";
    public static final String ORDER_ADD_STATUS_SUM = ADMIN + "Add a new status into order status line";
    public static final String ORDER_GET_DETAILS_BY_ID_SUM = USER + "Get an order bill details by id";
    public static final String ORDER_GET_ALL_BY_STATUS_SUM = ADMIN + "Get all order bill by status";
    public static final String ORDER_PATCH_ACCEPT_BY_ID_SUM = ADMIN + "Accept order bill by id";
    public static final String ORDER_PATCH_REFUSE_BY_ID_SUM = ADMIN + "Refuse order bill by id";
    public static final String ORDER_PATCH_SUCCEED_BY_ID_SUM = ADMIN + "Succeed order bill by id";
    public static final String ORDER_GET_ALL_BY_USER_ID_AND_STATUS_SUM = USER + "Get all order bill by user id and status";
}
