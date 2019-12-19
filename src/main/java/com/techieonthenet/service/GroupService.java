package com.techieonthenet.service;

import com.techieonthenet.entity.Group;

/**
 * The interface Group service.
 */
public interface GroupService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<Group> findAll();

    /**
     * Save group.
     *
     * @param group the group
     * @return the group
     */
    Group save(Group group);

    /**
     * Find by id group.
     *
     * @param groupId the group id
     * @return the group
     */
    Group findById(Long groupId);

    /**
     * Find by gst no group.
     *
     * @param gstNo the gst no
     * @return the group
     */
    Group findByGstNo(String gstNo);


}
