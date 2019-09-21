package com.techieonthenet.repository;

import com.techieonthenet.entity.Group;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {


}
