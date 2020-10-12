package com.project.web;//package com.lzy.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.acls.model.NotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
//
//@Component
//public class HandlerExceptionController extends HandlerExceptionResolverComposite {
//
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//			Exception ex) {
//
//		ModelAndView mv = new ModelAndView();
//
//		if (ex instanceof AccessDeniedException) {
//
//			mv.setStatus(HttpStatus.FORBIDDEN);
//			mv.setViewName("exception/403.html");
//		} else if (ex instanceof NotFoundException) {
//
//			mv.setStatus(HttpStatus.NOT_FOUND);
//			mv.setViewName("exception/404.html");
//		} else {
//
//			mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//			mv.setViewName("exception/500.html");
//		}
//
//		return mv;
//	}
//
//}
