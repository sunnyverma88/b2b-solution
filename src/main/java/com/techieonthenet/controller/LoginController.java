package com.techieonthenet.controller;

import com.techieonthenet.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Login controller.
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Login string.
     *
     * @param model the model
     * @return the string
     */
// Login form
    @GetMapping({"/", "/login"})
    public String login(Model model) {
        logger.info("Inside login page");
        model.addAttribute("userDto", new UserDto());
        return "login";
    }


    /**
     * Login error string.
     *
     * @param model the model
     * @return the string
     */
// Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        logger.info("Inside login Error");
        model.addAttribute("loginError", true);
        return "login";
    }

}
