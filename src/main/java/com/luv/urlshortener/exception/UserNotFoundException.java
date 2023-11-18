package com.luv.urlshortener.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends Exception {
    public String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
}
