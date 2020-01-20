package com.techieonthenet.service;

import com.techieonthenet.entity.Group;

public interface GroupService {

    Iterable<Group> findAll();

    Group save(Group group);

    Group findById(Long groupId);

    Group findByGstNo(String gstNo);


}
