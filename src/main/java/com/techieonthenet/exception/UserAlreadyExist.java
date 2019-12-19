package com.techieonthenet.exception;

/**
 * The type User already exist.
 */
public class UserAlreadyExist
        extends RuntimeException {
    /**
     * Instantiates a new User already exist.
     *
     * @param errorMessage the error message
     * @param err          the err
     */
    public UserAlreadyExist(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    /**
     * Instantiates a new User already exist.
     *
     * @param errorMessage the error message
     */
    public UserAlreadyExist(String errorMessage) {
        super(errorMessage);
    }
}