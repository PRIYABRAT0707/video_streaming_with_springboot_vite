package com.lernercurve.course.exception.exceptions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

import com.lernercurve.course.util.ExtraResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException {
	private static final String statusKey = "status";
	private static final String statusCodeKey = "statusCode";
	private static final String statusMessageKey = "statusMessage";
	private static final String pathKey = "path";
	private static final String timeStampKey = "timeStamp";

	private CustomException() {
	}

	public static <T, U, V, W, X> Map<String, Object> createException(T status, U statusCode, V statusMessage, W path,
			ExtraResponse<?>... extraResponse) {
		Map<String, Object> errorMap = new HashMap<>();
		try {
			errorMap.put(statusKey, status);
			errorMap.put(statusCodeKey, statusCode);
			errorMap.put(statusMessageKey, statusMessage);
			errorMap.put(pathKey, path);
			errorMap.put(timeStampKey, formatDateAndTime());
			for (ExtraResponse<?> res : extraResponse) {
				if (res != null) {
					errorMap.put(res.getResponseKey(), res.getResponseValue());
				}
			}
		} catch (Exception e) {
			log.info("unable to construct a error map:- {}", e.getMessage());
		}

		return errorMap;
	}

	public static String formatDateAndTime() {
		String formattedDate = "";
		try {
			formattedDate = ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
		} catch (Exception e) {
			log.info("unable to format date:-  {}", e.getMessage());
		}

		return formattedDate;
	}

}
