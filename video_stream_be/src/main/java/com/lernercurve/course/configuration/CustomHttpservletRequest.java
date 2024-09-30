package com.lernercurve.course.configuration;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomHttpservletRequest extends HttpServletRequestWrapper {

	public CustomHttpservletRequest(HttpServletRequest request) {
		super(request);
	}

	public String contextPath() {
		log.info("request:- {}", super.getContextPath());
		return super.getContextPath();
	}

}
