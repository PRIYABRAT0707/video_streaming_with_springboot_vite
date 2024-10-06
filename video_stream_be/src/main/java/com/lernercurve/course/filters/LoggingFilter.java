package com.lernercurve.course.filters;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(3)
public class LoggingFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("i am logging LoggingFilter before request:- {}",request);
			
			filterChain.doFilter(request, response);
			log.info("i am logging LoggingFilter after request completed wit response :- {}",response);
			
			
		} catch (Exception e) {
			log.info("i am logging LoggingFilter if error occured :- {}",e.getMessage());
		}finally {
			log.info("i am logging LoggingFilter finally :- {}");
		}
		
	}

}
