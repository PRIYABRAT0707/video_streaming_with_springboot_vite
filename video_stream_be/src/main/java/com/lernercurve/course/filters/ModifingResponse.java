package com.lernercurve.course.filters;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class ModifingResponse extends HttpServletResponseWrapper {

	  private int httpStatus = SC_OK; // Default to 200 OK
	
	public ModifingResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public void setStatus(int sc) {
		this.httpStatus = sc; // Store the status internally
		super.setStatus(sc);
	}

	public int getStatus() {
		return this.httpStatus;
	}
}
