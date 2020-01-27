package com.techieonthenet.service;

import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.TaskStatus;

import java.util.List;

/**
 * The interface Task service.
 */
public interface TaskService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<TaskItem> findAll();

    /**
     * Save task item.
     *
     * @param taskItem the task item
     * @return the task item
     */
    TaskItem save(TaskItem taskItem);

    /**
     * Find by id task item.
     *
     * @param id the id
     * @return the task item
     */
    TaskItem findById(Long id);

    /**
     * Find by parent task and task status task item.
     *
     * @param taskItem   the task item
     * @param taskStatus the task status
     * @return the task item
     */
    TaskItem findByParentTaskAndTaskStatus(TaskItem taskItem, TaskStatus taskStatus);

    /**
     * Create approval tasks.
     *
     * @param group the group
     * @param order the order
     */
    void createApprovalTasks(Group group, Order order) ;

    /**
     * Modify task.
     *
     * @param taskItem the task item
     * @param action   the action
     * @param user     the user
     */
    void modifyTask(TaskItem taskItem, String action, User user);

    /**
     * Find by user and task status list.
     *
     * @param user   the user
     * @param status the status
     * @return the list
     */
    List<TaskItem> findByUserAndTaskStatus(User user, TaskStatus status);

}
