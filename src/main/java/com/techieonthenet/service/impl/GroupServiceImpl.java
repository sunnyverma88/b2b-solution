package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Group;
import com.techieonthenet.repository.GroupRepository;
import com.techieonthenet.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group findById(Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    @Override
    public Group findByGstNo(String gstNo) {
        return groupRepository.findByGstNo(gstNo);
    }
}
