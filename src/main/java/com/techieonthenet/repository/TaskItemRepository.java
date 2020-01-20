package com.techieonthenet.repository;

import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.TaskStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskItemRepository extends PagingAndSortingRepository<TaskItem, Long> {

    List<TaskItem> findByUsersAndTaskStatus(List<User> users, TaskStatus status);

    TaskItem findByParentTaskItemAndTaskStatus(TaskItem taskItem, TaskStatus taskStatus);
}
