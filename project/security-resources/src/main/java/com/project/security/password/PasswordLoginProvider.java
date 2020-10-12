package com.project.security.password;

import com.project.common.enums.LoginTypeEnum;
import com.project.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordLoginProvider implements AuthenticationProvider {

	private PasswordLoginUserDetailService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void setUserDetailsService(PasswordLoginUserDetailService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public PasswordLoginProvider() {

		log.info("初始化密码登录验证类");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		PasswordLoginToken passwordLoginToken = (PasswordLoginToken) authentication;
		String username = (String) passwordLoginToken.getPrincipal();
		String password = (String) passwordLoginToken.getCredentials();

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (userDetails == null || !bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("密码错误");
		}

		authenticationChecks(userDetails);

		PasswordLoginToken authenticationResult = new PasswordLoginToken(userDetails.getAuthorities(),
				userDetails.getUsername(), userDetails.getPassword(), LoginTypeEnum.PASSWORD.detail);
		authenticationResult.setDetails(userDetails);

		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (PasswordLoginToken.class.isAssignableFrom(authentication));
	}

	private void authenticationChecks(UserDetails user) {
		if (!user.isAccountNonLocked()) {
			log.debug("用户已被锁定");

			throw new LockedException("用户已被锁定");
		}

		if (!user.isEnabled()) {
			log.debug("用户已失效，请联系管理员");

			throw new DisabledException("用户已失效，请联系管理员");
		}

		if (!user.isAccountNonExpired()) {
			log.debug("用户已过期，请联系管理员");

			throw new AccountExpiredException("用户已过期，请联系管理员");
		}

		if (!user.isCredentialsNonExpired()) {
			log.debug("密码已过期");
			throw new CredentialsExpiredException("密码已过期");
		}
	}

}
