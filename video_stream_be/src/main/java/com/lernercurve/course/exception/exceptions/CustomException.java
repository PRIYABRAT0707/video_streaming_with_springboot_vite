package com.lernercurve.course.exception.exceptions;

import java.util.HashMap;
import java.util.Map;

public class CustomException {
    private static final String statusKey = "status";
    private static final String statusCodeKey = "statusCode";
    private static final String statusMessageKey = "statusMessage";
    private static final String pathKey = "path";
    private static final String timeStampKey = "timeStamp";
    private static final String errorDetailsKey = "errorDetails";

    private CustomException() {
    }

    public static <T, U, V> Map<String, Object> createException(T status, U statusCode, V path) {
        Map<String, Object> errorMap = new HashMap<>();

        return errorMap;
    }

}
