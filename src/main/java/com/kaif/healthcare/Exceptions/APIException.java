package com.kaif.healthcare.Exceptions;

public class APIException extends RuntimeException {
    private static long serialVersionUID = 1L;

    public APIException(String message) {
        super(message);
    }
}
