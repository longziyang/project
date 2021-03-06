package com.project.common;

public class Constant {

    public static final String LOGIN_PAGE = "/sign";

    //邮箱验证码  存放redis 时需要功能名+用户名 存放邮箱验证码用   EMAIL_CODE+用户名作为key
    public static final String EMAIL_CODE = "EMAIL_CODE";

    public static final String SMS_CODE = "SMS_CODE";

    public static final String SIGN_IN = "SIGN_IN";

    public static final String BUSY_NETWORK = "BUSY_NETWORK";

    public static final String TOKEN_IS_EXPIRATION = "TOKEN_IS_EXPIRATION";

    public static final String TOKEN_IS_AUTHENTICATION = "TOKEN_IS_AUTHENTICATION";
    public static final String TOKEN_IS_INVALID = "TOKEN_IS_INVALID";
    public static final String USER_INFO_IS_INVALID = "USER_INFO_IS_INVALID";


}
