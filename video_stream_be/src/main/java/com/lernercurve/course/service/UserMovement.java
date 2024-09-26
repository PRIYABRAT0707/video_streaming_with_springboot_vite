package com.lernercurve.course.service;

import java.util.List;

import com.lernercurve.course.exception.exceptions.CourseNotFound;

public sealed interface UserMovement permits UserMovementImpl  {
	public static final String userName = "USERNAME";
	public static final String password = "PASSWORD";
	<T> List<?> getUserData(T userName) throws CourseNotFound;

}
