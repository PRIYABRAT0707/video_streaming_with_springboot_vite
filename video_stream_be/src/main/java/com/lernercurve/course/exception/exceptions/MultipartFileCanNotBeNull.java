package com.lernercurve.course.exception.exceptions;

public class MultipartFileCanNotBeNull extends Exception {

    public MultipartFileCanNotBeNull()  {
        super("file can not be empty");
    }

    public MultipartFileCanNotBeNull(String message)  {
        super(message);
    }
}
