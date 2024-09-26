package com.lernercurve.course.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("oneExtendingUserMovement")
public class OneExtendingUserMovement extends UserMovementImpl{

	@Override
	public <T> List<?> getUserData(T userName) {
		
		return Arrays.asList(this.userName,this.password,"ppandda");
	}

}
