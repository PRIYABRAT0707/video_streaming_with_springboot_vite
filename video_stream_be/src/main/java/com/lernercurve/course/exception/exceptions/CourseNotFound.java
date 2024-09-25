package com.lernercurve.course.exception.exceptions;

public class CourseNotFound extends Exception {
	private static final long serialVersionUID = 1L;

	public CourseNotFound()  {
        super("file can not be empty");
    }

    public CourseNotFound(String message)  {
        super(message);
    }
    public CourseNotFound(String message,Throwable th)  {
        super(message,th);
    }
    public CourseNotFound(Throwable th)  {
        super(th);
    }
}
