package com.techieonthenet.controller;

import com.techieonthenet.dto.UserDto;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.service.GroupService;
import com.techieonthenet.service.RoleService;
import com.techieonthenet.service.UserService;
import com.techieonthenet.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/add")
    public String addUser(Model model, Principal principal , @RequestParam (name ="message" , required = false) String message) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("groups", groupService.findAll());
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping("/add")
    public RedirectView addSubmittedUser(@ModelAttribute UserDto userDto, Model model , RedirectAttributes redirectAttributes) {
        String message="";
        try {
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleService.findByName(userDto.getRoleName()));
            userService.createUser(convertUserDtoToUser(userDto), userRoles);
            message ="User has been successfully added";
        } catch (Exception e) {
            logger.error("Exception adding user {}", e.getMessage());
            message = "Something Went Wrong ! Please contact administrator  -  Exception - " + e.getMessage();

        }
        redirectAttributes.addFlashAttribute("message" , message);
        return new RedirectView("/user/add");
    }

    private User convertUserDtoToUser(UserDto userDto)
    {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(AppUtils.getAlphaNumericString(8));
        user.setEmail(userDto.getEmail());
        logger.info("Password : {}", user.getPassword());
        if (userDto.getGroupId() != null) user.setGroup(groupService.findById(userDto.getGroupId()));
        return user;
    }

}
