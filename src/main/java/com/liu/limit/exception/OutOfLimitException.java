package com.liu.limit.exception;

public class OutOfLimitException extends Exception {
    private String message;

    public OutOfLimitException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
