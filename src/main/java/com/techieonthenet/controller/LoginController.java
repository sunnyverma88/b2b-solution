package com.techieonthenet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Login form
    @GetMapping({"/", "/login"})
    public String login() {
        logger.info("Inside login Mapping");

        return "login-page";
    }

    @PostMapping({"/login-submit"})
    public String loginSubmit() {
        logger.info("Inside login Mapping");
        return "product";
    }

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login-page";
    }
}
