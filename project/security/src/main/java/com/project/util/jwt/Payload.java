package com.project.util.jwt;

import java.io.Serializable;
import java.util.Date;

public class Payload<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //用户id
    private String id;
    //用户信息
    private T userInfo;
    //过期时间
    private Date expiration;
    //登录方式 手机 密码 邮箱 第三方登录
    private String loginType;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

}
