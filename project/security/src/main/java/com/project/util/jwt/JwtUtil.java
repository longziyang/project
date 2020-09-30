package com.project.util.jwt;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtil {

	private static final String JWT_PAYLOAD_USER_KEY = "user";
	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	private static String issuer = "dance-up";

	/**
	 * 加密生成token
	 *
	 * @param object 载体信息
	 * @param maxAge 有效时长
	 * @param secret 服务器私钥
	 * @param <T>
	 * @return
	 */
	public static String createToken(Object object, long maxAge, String secret) {

		try {

			String userInfo = JSON.toJSONString(object);
			final Algorithm signer = Algorithm.HMAC256(secret);// 生成签名
			String token = JWT.create().withIssuer(issuer).withSubject("createToken")// 主题，科目
					.withClaim(JWT_PAYLOAD_USER_KEY, userInfo).withExpiresAt(new Date(System.currentTimeMillis() + maxAge))
					.sign(signer);

			return token;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("生成token异常：", e);
			return null;
		}
	}

	/**
	 * 解析验证token
	 *
	 * @param token  加密后的token字符串
	 * @param secret 服务器私钥
	 * @return
	 * @return
	 */
	public static <T> T verifyToken(String token, String secret, Class<T> clazz) {

		try {
			
			if (StringUtils.isNotBlank(secret) && StringUtils.isNotBlank(token)) {
				
				Algorithm algorithm = Algorithm.HMAC256(secret);
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT jwt = verifier.verify(token);
				Claim claim = jwt.getClaim(JWT_PAYLOAD_USER_KEY);
				String str = claim.asString();
				return JSON.parseObject(str, clazz);
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	public static <T> T getObject(String token, String secret, Class<T> clazz) {

		final Algorithm signer = Algorithm.HMAC256(secret);// 生成签名
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		String str = (String) claims.get(JWT_PAYLOAD_USER_KEY);
		return JSON.parseObject(str, clazz);
	}

}