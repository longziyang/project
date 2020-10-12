package com.project.util.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookieUtils {

	public static void setCookie(HttpServletResponse response, String name, String value) {
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "Utf-8"));
			cookie.setPath("/");

			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static String getCookie(HttpServletRequest arg0, String name) {

		Cookie[] cookies = arg0.getCookies();

		if (cookies == null || cookies.length == 0) {

			return null;
		}

		for (Cookie cookie : cookies) {

			if (name.equals(cookie.getName())) {

				try {

					return URLEncoder.encode(cookie.getValue(), "Utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}
