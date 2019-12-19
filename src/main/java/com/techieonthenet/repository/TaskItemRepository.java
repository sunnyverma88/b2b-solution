package com.techieonthenet.repository;

import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.TaskStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Task repository.
 */
@Repository
public interface TaskItemRepository extends PagingAndSortingRepository<TaskItem, Long> {

    /**
     * Find by user and task status list.
     *
     * @param users  the user
     * @param status the status
     * @return the list
     */
    List<TaskItem> findByUsersAndTaskStatus(List<User> users, TaskStatus status);

    /**
     * Find by parent task item and task status task item.
     *
     * @param taskItem   the task item
     * @param taskStatus the task status
     * @return the task item
     */
    TaskItem findByParentTaskItemAndTaskStatus(TaskItem taskItem, TaskStatus taskStatus);
}
