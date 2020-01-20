package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import com.techieonthenet.entity.common.TaskStatus;
import com.techieonthenet.entity.common.TaskType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "task_items")
@Getter
@Setter
@SequenceGenerator(name = "task_item_generator", sequenceName = "task_item_seq", allocationSize = 1)
public class TaskItem extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_item_generator")
    private Long id;

    private String taskName;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private String remarks;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private TaskItem parentTaskItem;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tasks_users", joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Collection<User> users = new ArrayList<>();

}
