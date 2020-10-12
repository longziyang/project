package com.project.common.enums;


/**
 * 建议使用授权码模式来获取token信息
 */
public enum OAuth2GrantTypeEnum {

    CLIENT_CREDENTIALS("client_credentials", "客户端模式"), IMPLICIT("implicit", "简化模式"),
    REFRESH_TOKEN("refresh_token", "刷新token"), PASSWORD("password", "密码模式"),
    AUTHORIZATION_CODE("authorization_code", "授权码模式");

    public String type;
    public String detail;

    OAuth2GrantTypeEnum(String type, String detail) {

        this.type = type;
        this.detail = detail;
    }
}
