package com.techieonthenet.repository;

public interface FileRepository {

    void storeFile(String clientId, String type, byte[] bytes);
}