package com.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ysh create by 2020-9-12 22:38:40
 * 
 */

@Component
public class ResourcesDataConfig {

	@Value("${spring.oauth.code_url}")
	private String codeUrl;
	@Value("${spring.oauth.authorization_code_url}")
	private String authorizationCodeUrl;
	@Value("${spring.oauth.refresh_token_url}")
	private String refreshTokenUrl;
	@Value("${spring.oauth.password_url}")
	private String passwordUrl;
	@Value("${spring.oauth.implicit_url}")
	private String implicitUrl;
	@Value("${spring.oauth.client_credentials_url}")
	private String clientCredentialsUrl;

	@Value("${spring.oauth.client_id}")
	private String clientId;
	@Value("${spring.oauth.grant_type}")
	private String grantType;
	@Value("${spring.oauth.secret}")
	private String secret;

	public String getClientId() {

		return clientId;
	}

	public String getCodeUrl() {

		return codeUrl;
	}

	public String getGrantType() {

		return grantType;
	}

	public String getPasswordUrl() {
		return passwordUrl;
	}

	public String getImplicitUrl() {
		return implicitUrl;
	}

	public String getClientCredentialsUrl() {
		return clientCredentialsUrl;
	}

	public String getAuthorizationCodeUrl() {
		return authorizationCodeUrl;
	}

	public String getRefreshTokenUrl() {
		return refreshTokenUrl;
	}

	public String getSecret() {
		return secret;
	}


}
