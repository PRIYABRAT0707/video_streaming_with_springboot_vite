package com.lernercurve.course.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.lernercurve.course.exception.exceptions.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LernerUtil {
	private static final String statusKey = "status";
	private static final String statusCodeKey = "statusCode";
	private static final String statusMessageKey = "statusMessage";
	private static final String timeStampKey = "timeStamp";
	private static final String responseKey = "response";

	public static <V, W> Map<String, Object> responseStructure(V statusMessage, W response,
			ExtraResponse<?>... extraResponse) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			responseMap.put(statusKey, HttpStatus.OK);
			responseMap.put(statusCodeKey, HttpStatus.OK.value());
			responseMap.put(statusMessageKey, statusMessage);
			responseMap.put(timeStampKey, CustomException.formatDateAndTime());
			responseMap.put(responseKey, response);
			for (ExtraResponse<?> res : extraResponse) {
				if (res != null) {
					responseMap.put(res.getResponseKey(), res.getResponseValue());
				}
			}
		} catch (Exception e) {
			log.info("unable to construct a response map:- {}", e.getMessage());
		}

		return responseMap;
	}

}
