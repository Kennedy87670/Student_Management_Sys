package com.forAll.sms.errorAndException;

public class GradeNotFoundException extends Exception{
    public GradeNotFoundException() {
        super();
    }

    public GradeNotFoundException(String message) {
        super(message);
    }

    public GradeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GradeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
