package com.easyEcom.exception;

import java.time.LocalDateTime;

public class MyErrorDetails {

    private LocalDateTime dateTime;
    private String message;
    private String details;

    public MyErrorDetails() {
    }

    public MyErrorDetails(LocalDateTime dateTime, String message, String details) {
        this.dateTime = dateTime;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ExceptionDetails{" +
                "dateTime=" + dateTime +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
