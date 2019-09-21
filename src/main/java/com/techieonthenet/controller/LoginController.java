package com.techieonthenet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Login form
    @GetMapping({"/", "/login"})
    public String login() {
        logger.info("Inside login page");

        return "login-page";
    }


    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        logger.info("Inside login Error");
        model.addAttribute("loginError", true);
        return "login-page";
    }


}
