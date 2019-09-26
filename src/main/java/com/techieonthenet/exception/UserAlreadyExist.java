package com.techieonthenet.exception;

public class UserAlreadyExist
  extends RuntimeException {
    public UserAlreadyExist(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public UserAlreadyExist(String errorMessage) {
        super(errorMessage);
    }
}