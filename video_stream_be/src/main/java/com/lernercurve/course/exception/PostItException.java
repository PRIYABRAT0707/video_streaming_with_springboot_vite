package com.lernercurve.course.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.lernercurve.course.exception.exceptions.CustomException;
import com.lernercurve.course.exception.exceptions.MultipartFileCanNotBeNull;

@RestControllerAdvice
public class PostItException {

	@ExceptionHandler(MultipartFileCanNotBeNull.class)
	public ResponseEntity<Map<String, Object>> multipartFileException(
			MultipartFileCanNotBeNull multipartFileCanNotBeNull, WebRequest request) {

		return ResponseEntity.badRequest()
				.body(CustomException.createException(HttpStatus.BAD_REQUEST.getReasonPhrase(),
						HttpStatus.BAD_REQUEST.value(), multipartFileCanNotBeNull.getMessage(),
						request.getDescription(true), multipartFileCanNotBeNull.getCause()));
	}
}
