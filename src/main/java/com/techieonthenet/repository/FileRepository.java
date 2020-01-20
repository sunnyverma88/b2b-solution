package com.techieonthenet.repository;

/**
 * The interface File repository.
 */
public interface FileRepository {

    /**
     * Store file.
     *
     * @param clientId the client id
     * @param type     the type
     * @param bytes    the bytes
     */
    void storeFile(String clientId, String type, byte[] bytes);
}