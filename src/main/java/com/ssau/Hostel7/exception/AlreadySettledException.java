package com.ssau.Hostel7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AlreadySettledException extends RuntimeException {

    public AlreadySettledException(String message) {
        super(message);
    }

}
