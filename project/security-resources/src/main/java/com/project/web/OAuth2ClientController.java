package com.project.web;

import com.project.service.OAuth2ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/res")
public class OAuth2ClientController {

	@Autowired
	private OAuth2ClientService oAuth2ClientService;

	@RequestMapping()
	public void getToken(@RequestParam(value = "code") String code,HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("code = " + code);
		oAuth2ClientService.getToken(code, request, response);
	}

	@RequestMapping("/implicit")
	public void getIToken(@RequestParam(value = "#accessToken", required = false) String accessToken,
			@RequestParam(value = "tokenType", required = false) String tokenType,
			@RequestParam(value = "expiresIn", required = false) String expiresIn,
			@RequestParam(value = "scope", required = false) String scope, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println(request.getRequestURL());
		// oAuth2ClientService.getToken(code, request, response);
	}
}
