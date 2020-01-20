package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;

/**
 * The interface Privilege service.
 */
public interface PrivilegeService {

    /**
     * Save.
     *
     * @param priv the priv
     */
    void save(Privilege priv);

    /**
     * Create priv privilege.
     *
     * @param priv the priv
     * @return the privilege
     */
    Privilege createPriv(Privilege priv);

    /**
     * Find by name privilege.
     *
     * @param name the name
     * @return the privilege
     */
    Privilege findByName(String name);
}
