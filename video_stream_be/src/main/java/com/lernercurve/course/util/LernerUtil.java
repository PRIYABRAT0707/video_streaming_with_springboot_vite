package com.lernercurve.course.util;

import java.util.HashMap;
import java.util.Map;

import com.lernercurve.course.exception.exceptions.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LernerUtil {
	private static final String statusKey = "status";
	private static final String statusCodeKey = "statusCode";
	private static final String statusMessageKey = "statusMessage";
	private static final String timeStampKey = "timeStamp";
	private static final String responseKey = "response";

	@SafeVarargs
	public static <T, U, V, W, X> Map<String, Object> responseStructure(T status, U statusCode, V statusMessage,
			W response,  ExtraResponse<X>... extraResponse) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			responseMap.put(statusKey, status);
			responseMap.put(statusCodeKey, statusCode);
			responseMap.put(statusMessageKey, statusMessage);
			responseMap.put(timeStampKey, CustomException.formatDateAndTime());
			responseMap.put(responseKey, response);
			for (ExtraResponse<X> res : extraResponse) {
				responseMap.put(res.getResponseKey(), res.getResponseValue());
			}
		} catch (Exception e) {
			log.info("unable to construct a hashmap:- {}", e.getMessage());
		}

		return responseMap;
	}

}
