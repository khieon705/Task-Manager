package com.kenty.exception;

public class NoTaskFoundException extends RuntimeException {
    public NoTaskFoundException(String message) {
        super(message);
    }
}
