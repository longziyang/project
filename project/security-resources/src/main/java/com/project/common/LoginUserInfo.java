package com.project.common;

import com.project.entity.SysUser;

public class LoginUserInfo {

	private static SysUser user;

	private LoginUserInfo() {
	}

	public static void createUser(SysUser sysUser) {

		user = sysUser;
	}

	public static SysUser getUser() {

		return user;
	}

	public static Long getUserId() {

		return user == null ? null : user.getId();
	}

	public static String getUsername() {

		return user == null ? null : user.getUsername();
	}

}
