package com.project.web;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(RuntimeException.class)
	public String exception(RuntimeException e) {

		if (e instanceof AccessDeniedException) {

			return "/exception/403.html";
		}
		return "exception/500.html";
	}

}
