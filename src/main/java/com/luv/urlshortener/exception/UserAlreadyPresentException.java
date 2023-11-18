package com.luv.urlshortener.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyPresentException extends Exception {
    public String message;

    public UserAlreadyPresentException(String message) {
        this.message = message;
    }
}
