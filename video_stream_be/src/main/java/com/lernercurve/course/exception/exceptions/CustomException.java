package com.lernercurve.course.exception.exceptions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException {
	private static final String statusKey = "status";
	private static final String statusCodeKey = "statusCode";
	private static final String statusMessageKey = "statusMessage";
	private static final String pathKey = "path";
	private static final String timeStampKey = "timeStamp";
	private static final String errorDetailsKey = "errorDetails";

	private CustomException() {
	}

	public static <T, U, V, W, X> Map<String, Object> createException(T status, U statusCode, V statusMessage, W path,
			X errorDetails) {
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put(statusKey, status);
		errorMap.put(statusCodeKey, statusCode);
		errorMap.put(statusMessageKey, statusMessage);
		errorMap.put(pathKey, path);
		errorMap.put(timeStampKey, formatDateAndTime());
		errorMap.put(errorDetailsKey, errorDetails);
		return errorMap;
	}
	
	private static String formatDateAndTime() {
		String formattedDate="";
		try {
			formattedDate=ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
		} catch (Exception e) {
			log.info("unable to format date:-  {}",e.getMessage());
		}
		
		return formattedDate;
	}

}
