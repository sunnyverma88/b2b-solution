package com.techieonthenet.controller;


import com.techieonthenet.dto.TaskDto;
import com.techieonthenet.entity.TaskItem;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.TaskStatus;
import com.techieonthenet.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/task")
@Controller
public class TaskController {

    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/pending")
    public String getAllPendingTaskByUser(HttpSession session, Model model, @RequestParam(name = "message", required = false) String message) {
        User user = (User) session.getAttribute("user");
        List<TaskItem> tasks = taskService.findByUserAndTaskStatus(user, TaskStatus.PENDING_APPROVAL);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskDto", new TaskDto());
        session.setAttribute("tasks", tasks);
        if (message != null)
            model.addAttribute("message", message);
        return "pending-tasks";
    }

    @PostMapping("/modify")
    public RedirectView modifyTask(HttpSession session, @ModelAttribute TaskDto taskDto, RedirectAttributes redirectAttributes) throws Exception {
        try {
            User user = (User) session.getAttribute("user");
            logger.info("Task ID : {} , Action : {}", taskDto.getId(), taskDto.getAction());
            TaskItem task = taskService.findById(taskDto.getId());
            task.setRemarks(taskDto.getRemarks());
            taskService.modifyTask(task, taskDto.getAction(), user);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        }
        return new RedirectView("/task/pending");
    }

}
