package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;

public interface PrivilegeService {

    void save(Privilege priv);

    Privilege createPriv(Privilege priv);

    Privilege findByName(String name);
}
