package com.kenty.model;

import java.time.LocalDateTime;

public class ErrorMessage {
    private final int status;
    private final String message;
    private final LocalDateTime timeStamp;

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
        timeStamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
