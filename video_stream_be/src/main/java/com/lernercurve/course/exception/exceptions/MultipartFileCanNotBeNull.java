package com.lernercurve.course.exception.exceptions;

public class MultipartFileCanNotBeNull extends Exception {

	private static final long serialVersionUID = 1L;

	public MultipartFileCanNotBeNull()  {
        super("file can not be empty");
    }

    public MultipartFileCanNotBeNull(String message)  {
        super(message);
    }
    public MultipartFileCanNotBeNull(String message,Throwable th)  {
        super(message,th);
    }
    public MultipartFileCanNotBeNull(Throwable th)  {
        super(th);
    }
}
