package com.techieonthenet.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * The type Authority.
 */
public class Authority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 123123L;

    private final String authority;

    /**
     * Instantiates a new Authority.
     *
     * @param authority the authority
     */
    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}