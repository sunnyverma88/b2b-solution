package com.techieonthenet.service;

import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.TaskStatus;

import java.util.List;

public interface TaskService {

    Iterable<TaskItem> findAll();

    TaskItem save(TaskItem taskItem);

    TaskItem findById(Long id);

    TaskItem findByParentTaskAndTaskStatus(TaskItem taskItem, TaskStatus taskStatus);

    void createApprovalTasks(Group group, Order order);

    void modifyTask(TaskItem taskItem, String action, User user);

    List<TaskItem> findByUserAndTaskStatus(User user, TaskStatus status);

}
