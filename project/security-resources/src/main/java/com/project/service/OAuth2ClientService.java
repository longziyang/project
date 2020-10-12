package com.project.service;

import com.alibaba.fastjson.JSON;
import com.project.common.LoginUserInfo;
import com.project.common.enums.OAuth2GrantTypeEnum;
import com.project.config.ResourcesDataConfig;
import com.project.util.http.HttpUtils;
import com.project.vo.OAuthResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Transactional
public class OAuth2ClientService {

	@Autowired
	private ResourcesDataConfig resourcesDataConfig;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static String isSuccess = "{\"access_token";
	private static String oauthKey = "oauthKey";

	private static final Logger logger = LoggerFactory.getLogger(OAuth2ClientService.class);

	public void getToken(String code, HttpServletRequest request, HttpServletResponse response) {
		String username = LoginUserInfo.getUsername();
		String grantType = resourcesDataConfig.getGrantType();

		if (StringUtils.isBlank(grantType))
			throw new NullPointerException("grant_type is null");

		String result = getUrl(grantType, code);
		if (StringUtils.isNotBlank(result) && result.startsWith(isSuccess)) {

			try {

				OAuthResponseVO vo = JSON.parseObject(result, OAuthResponseVO.class);
				Long expires = Long.valueOf(vo.getExpiresIn());
				vo.setExpirDate(System.currentTimeMillis() + expires);
				redisTemplate.opsForHash().put(oauthKey, username, JSON.toJSONString(vo));

				response.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new RuntimeException("");
		}

	}

	private String getUrl(String grantType, String code) {

		String clientId = resourcesDataConfig.getClientId();
		String secret = resourcesDataConfig.getSecret();

		if (!grantType.equals(OAuth2GrantTypeEnum.AUTHORIZATION_CODE.type))
			throw new RuntimeException("");

		String authorizationCodeUrl = resourcesDataConfig.getAuthorizationCodeUrl();
		String sendUrl = String.format(authorizationCodeUrl, grantType, clientId, secret, code);

		String res = HttpUtils.httpPostRequest(sendUrl);
		logger.info("oauth2资源返回信息  " + res);
		return res;
	}

}
