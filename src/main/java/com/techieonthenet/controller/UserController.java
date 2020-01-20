package com.techieonthenet.controller;

import com.techieonthenet.dto.UserDto;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.service.EmailService;
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

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The type User controller.
 */
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

    /**
     * The Email service.
     */
    @Autowired
    EmailService emailService;

    /**
     * Add user string.
     *
     * @param model     the model
     * @param principal the principal
     * @param message   the message
     * @return the string
     */
    @GetMapping("/add")
    public String addUser(Model model, Principal principal, @RequestParam(name = "message", required = false) String message) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("groups", groupService.findAll());
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    /**
     * Add submitted user redirect view.
     *
     * @param userDto            the user dto
     * @param redirectAttributes the redirect attributes
     * @return the redirect view
     */
    @PostMapping("/add")
    public RedirectView addSubmittedUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleService.findByName(userDto.getRoleName()));
            String generatedPassword = AppUtils.getAlphaNumericString(8);
            userDto.setPassword(generatedPassword);
            User user = convertUserDtoToUser(userDto);
            userService.createUser(user, userRoles);

            sendEmailToUser(user, generatedPassword, "new-user", "Welcome to Apprize !!");
            message = "User has been successfully added";
        } catch (Exception e) {
            logger.error("Exception adding user {}", e.getMessage());
            message = "Something Went Wrong ! Please contact administrator  -  Exception - " + e.getMessage();

        }
        redirectAttributes.addFlashAttribute("message", message);
        return new RedirectView("/user/add");
    }

    private User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setApproverType(userDto.getApproverType());
        user.setPasswordResetRequired(true);
        if (userDto.getGroupId() != null) user.setGroup(groupService.findById(userDto.getGroupId()));
        return user;
    }

    private void sendEmailToUser(User user, String genPassword, String templateName, String subject) throws IOException, MessagingException {


        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("user", user);
        valueMap.put("genPass", genPassword);
        emailService.sendSimpleMessage(user.getEmail(), subject, valueMap, templateName);
    }

    /**
     * Forgot password redirect view.
     *
     * @param userDto the user dto
     * @return the redirect view
     * @throws IOException        the io exception
     * @throws MessagingException the messaging exception
     */
    @PostMapping("/forgotPwd")
    public RedirectView forgotPassword(@ModelAttribute UserDto userDto) throws IOException, MessagingException {
        try {
            logger.info("Inside Forgot Password - {}", userDto.getEmail());
            User user = userService.findByUsernameAndEnabled(userDto.getEmail());
            user.setPasswordResetRequired(true);
            String generatedPwd = AppUtils.getAlphaNumericString(8);
            user.setPassword(generatedPwd);
            sendEmailToUser(user, generatedPwd, "new-user", "Welcome to Apprize !!");
            userService.save(user);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("/login");
    }

}
