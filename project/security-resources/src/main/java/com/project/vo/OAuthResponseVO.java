package com.project.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 *
 * @author ysh create by 2020-9-13 21:21:55
 * 
 */

public class OAuthResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		String val = "{\"access_token\":\"651c870f-43b8-4086-b02f-1fcd6d60f450\",\"token_type\":\"bearer\",\"refresh_token\":\"1432c858-2fda-43aa-a406-8e6b405458d8\",\"expires_in\":35301,\"scope\":\"\\r\\nwrite read\"}";
		OAuthResponseVO vo = JSON.parseObject(val, OAuthResponseVO.class);

		System.out.println(val.startsWith("{\"access_token"));
	}

	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private String expiresIn;
	private String scope;
	
	//过期时间戳
	private Long expirDate;

	@Override
	public String toString() {
		return "OAuthResponseVO [accessToken=" + accessToken + ", tokenType=" + tokenType + ", refreshToken="
				+ refreshToken + ", expiresIn=" + expiresIn + ", scope=" + scope + "]";
	}

	
	public Long getExpirDate() {
		return expirDate;
	}


	public void setExpirDate(Long expirDate) {
		this.expirDate = expirDate;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
