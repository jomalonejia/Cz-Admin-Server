package com.cz.security3.security;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Error model for interacting with client.
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
public class ErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;


    private final Date timestamp;

    protected ErrorResponse(final String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Date();
    }

    public static ErrorResponse of(final String message, HttpStatus status) {
        return new ErrorResponse(message , status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
