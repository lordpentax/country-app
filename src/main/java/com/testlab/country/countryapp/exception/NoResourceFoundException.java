package com.testlab.country.countryapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoResourceFoundException extends RuntimeException {

    private static final long serialVersionUID = 3582946070364887945L;

    public NoResourceFoundException(final String message) {
        super(message);
    }
}
