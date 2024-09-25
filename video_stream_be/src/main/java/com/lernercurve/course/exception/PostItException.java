package com.lernercurve.course.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.lernercurve.course.exception.exceptions.CourseNotFound;
import com.lernercurve.course.exception.exceptions.CustomException;
import com.lernercurve.course.exception.exceptions.MultipartFileCanNotBeNull;
import com.lernercurve.course.helpers.PostItHelper;
import com.lernercurve.course.util.ExtraResponse;

@RestControllerAdvice
public class PostItException {

	@ExceptionHandler(MultipartFileCanNotBeNull.class)
	public ResponseEntity<Map<String, Object>> multipartFileException(
			MultipartFileCanNotBeNull multipartFileCanNotBeNull, WebRequest request) {

		return ResponseEntity.badRequest().body(CustomException.createException(
				HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(),
				multipartFileCanNotBeNull.getMessage(), request.getDescription(true),
				new ExtraResponse<Throwable>(PostItHelper.errorDetailsKey, multipartFileCanNotBeNull.getCause())));
	}
	@ExceptionHandler(CourseNotFound.class)
	public ResponseEntity<Map<String, Object>> courseNotFoundException(
			CourseNotFound courseNotFound, WebRequest request) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(CustomException.createException(
				HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(),
				courseNotFound.getMessage(), request.getDescription(true),
				new ExtraResponse<Throwable>(PostItHelper.errorDetailsKey, courseNotFound.getCause())));
	}
}
