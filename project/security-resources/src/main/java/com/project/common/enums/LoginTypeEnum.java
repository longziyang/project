package com.project.common.enums;

public enum LoginTypeEnum {

    //QQ("qq"), WECHAT("wechat"),
    PASSWORD("password"),SMS("sms"), EMAIL("email"), OAUTH("oauth");

    public String detail;

    LoginTypeEnum(String detail) {

        this.detail = detail;
    }
}
