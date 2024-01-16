package com.forAll.sms.errorAndException;

public class ComplaintNotFoundException extends Exception{
    public ComplaintNotFoundException() {
        super();
    }

    public ComplaintNotFoundException(String message) {
        super(message);
    }

    public ComplaintNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComplaintNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ComplaintNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
