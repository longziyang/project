package com.project.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 决策器
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

	private final static Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	/**
	 * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
	 *
	 * @param authentication   包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
	 * @param object           就是FilterInvocation对象，可以得到request等web资源
	 * @param configAttributes configAttributes是本次访问需要的权限
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			if (authentication == null) {
				throw new AccessDeniedException("当前访问没有权限");
			}
			ConfigAttribute ca = iterator.next();
			// 当前请求需要的权限
			String needRole = ca.getAttribute();
			if ("ROLE_LOGIN".equals(needRole)) {
				if (authentication instanceof AnonymousAuthenticationToken) {
					throw new BadCredentialsException("未登录");
				} else
					return;
			}
			// 当前用户所具有的权限
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足!");
	}

	/**
	 * 表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
	 */
	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	/**
	 * 表示当前AccessDecisionManager实现是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

}
