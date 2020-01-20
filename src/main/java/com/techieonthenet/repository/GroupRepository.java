package com.techieonthenet.repository;

import com.techieonthenet.entity.Group;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Group repository.
 */
@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {


    /**
     * Find by gst no group.
     *
     * @param gstNo the gst no
     * @return the group
     */
    Group findByGstNo(String gstNo);
}
