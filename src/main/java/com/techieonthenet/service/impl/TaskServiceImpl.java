package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.ApproverType;
import com.techieonthenet.entity.common.OrderStatus;
import com.techieonthenet.entity.common.TaskStatus;
import com.techieonthenet.entity.common.TaskType;
import com.techieonthenet.exception.UserDefinedException;
import com.techieonthenet.repository.TaskItemRepository;
import com.techieonthenet.service.GroupService;
import com.techieonthenet.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    TaskItemRepository taskRepository;

    @Autowired
    GroupService groupService;

    @Override
    public Iterable<TaskItem> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public TaskItem save(TaskItem taskItem) {
        return taskRepository.save(taskItem);
    }

    @Override
    public TaskItem findById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<TaskItem> findByUserAndTaskStatus(User user, TaskStatus status) {
        List<User> users = new ArrayList<>();
        users.add(user);
        return taskRepository.findByUsersAndTaskStatus(users, status);
    }

    @Override
    public void createApprovalTasks(Group group, Order order) {

        createLevel1Task(group, order);
    }

    public boolean createLevel1Task(Group group, Order order) {
        boolean taskCreated = false;
        List<User> users = groupService.findById(group.getId()).getUsers();
        TaskItem taskItem = new TaskItem();
        taskItem.setOrder(order);
        taskItem.setTaskType(TaskType.ORDER_APPROVAL_LEVEL_1);
        taskItem.setParentTaskItem(taskItem);
        taskItem.setTaskName("ORDER APPROVAL -  LEVEL 1 TASK");
        taskItem.setTaskStatus(TaskStatus.PENDING_APPROVAL);
        for (User user : users) {
            if (user.getApproverType().equals(ApproverType.LEVEL_1) && user.isEnabled()) {
                taskItem.getUsers().add(user);
                taskCreated = true;
            }
        }

        if (taskCreated == Boolean.FALSE)
            throw new UserDefinedException(UserDefinedException.ORDER_APPROVAL_LEVEL_1_APPROVER_NOT_PRESENT);
        createLevel2Task(group, order, taskItem);
        order.getTaskItems().add(taskItem);
        return taskCreated;
    }

    public boolean createLevel2Task(Group group, Order order, TaskItem parentTask) {
        boolean taskCreated = false;
        List<User> users = groupService.findById(group.getId()).getUsers();
        TaskItem taskItem2 = new TaskItem();
        taskItem2.setOrder(order);
        taskItem2.setTaskType(TaskType.ORDER_APPROVAL_LEVEL_2);
        taskItem2.setParentTaskItem(parentTask);
        taskItem2.setTaskName("ORDER APPROVAL -  LEVEL 2 TASK");
        taskItem2.setTaskStatus(TaskStatus.WAITING);
        for (User user : users) {
            if (user.getApproverType().equals(ApproverType.LEVEL_2) && user.isEnabled()) {
                taskItem2.getUsers().add(user);
                taskCreated = true;
            }
        }
        if (taskCreated == Boolean.FALSE)
            throw new UserDefinedException(UserDefinedException.ORDER_APPROVAL_LEVEL_2_APPROVER_NOT_PRESENT);
        order.getTaskItems().add(taskItem2);
        return taskCreated;
    }

    @Override
    public void modifyTask(TaskItem task, String action, User user) {
        Boolean taskModified = false;
        for (User user1 : task.getUsers()) {
            logger.info("Logged User ID - {} Task User Id - {}", user.getId(), user1.getId());
            if (user1.getId().equals(user.getId()) && action.equalsIgnoreCase("Approve")
                    && task != null && task.getTaskStatus() != TaskStatus.APPROVED) {
                task.setTaskStatus(TaskStatus.APPROVED);
                TaskItem childTask = findByParentTaskAndTaskStatus(task, TaskStatus.WAITING);
                if (childTask != null) {
                    childTask.setTaskStatus(TaskStatus.PENDING_APPROVAL);
                    save(childTask);
                    taskModified = true;
                } else {
                    task.getOrder().setOrderStatus(OrderStatus.APPROVED_PENDING_SHIPMENT);
                    save(task);
                    taskModified = true;
                }
            } else if (action.equalsIgnoreCase("Reject") &&
                    task != null && task.getTaskStatus() != TaskStatus.REJECTED) {
                task.setTaskStatus(TaskStatus.REJECTED);
                TaskItem childTask = findByParentTaskAndTaskStatus(task, TaskStatus.WAITING);
                task.getOrder().setOrderStatus(OrderStatus.REJECTED);
                if (childTask != null) {
                    childTask.setTaskStatus(TaskStatus.REJECTED);
                    save(childTask);
                    taskModified = true;
                } else {
                    save(task);
                    taskModified = true;
                }
            }
        }
        if (!taskModified) throw new UserDefinedException(UserDefinedException.UNAUTHORIZED_ACTION);
    }

    public TaskItem findByParentTaskAndTaskStatus(TaskItem taskItem, TaskStatus taskStatus) {
        return taskRepository.findByParentTaskItemAndTaskStatus(taskItem, taskStatus);
    }
}
