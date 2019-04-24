package com.epam.xml.exception;

public class BasicException extends Exception {

    public BasicException(String string) {
        super(string);
    }

    public BasicException() {
    }

    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }

}