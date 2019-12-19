package com.techieonthenet.service;

/**
 * The interface Security service.
 */
public interface SecurityService {
    /**
     * Find logged in username string.
     *
     * @return the string
     */
    String findLoggedInUsername();

    /**
     * Auto login.
     *
     * @param username the username
     * @param password the password
     */
    void autoLogin(String username, String password);
}