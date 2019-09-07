package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;

import java.util.Set;

public interface PrivilegeService {

    void save(Privilege priv);
    public Privilege createPriv (Privilege priv);
    public Privilege findByName(String name);
}
