package com.lernercurve.course.configuration;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class CustomHttpServletResponse extends HttpServletResponseWrapper {

	public CustomHttpServletResponse(HttpServletResponse response) {
		super(response);
		
	}

}
