package com.formidspike.Classes;

public class CustomRuntimeException extends RuntimeException {
    private final int returnCode;

    public CustomRuntimeException(int returnCode, String message) {
        super(message);
        this.returnCode = returnCode;
    }

    public int getReturnCode() {
        return returnCode;
    }
}