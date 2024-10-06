package com.lernercurve.course.filters;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class ResponseModifierFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("i am logging ResponseModifierFilter before request:- {}",request);
			var modifingResponse=new ModifingResponse(response);
			modifingResponse.addCookie(new Cookie("ppanda", "spanda"));
			modifingResponse.setStatus(modifingResponse.SC_BAD_GATEWAY);;
			filterChain.doFilter(request, modifingResponse);
			log.info("i am logging ResponseModifierFilter after request completed wit response :- {}",response.getStatus());
			modifingResponse.setStatus(modifingResponse.SC_BAD_GATEWAY);;
			
		} catch (Exception e) {
			log.info("i am logging ResponseModifierFilter if error occured :- {}",e.getMessage());
		}finally {
			log.info("i am logging ResponseModifierFilter finally :- {}");
		}
	}
}
