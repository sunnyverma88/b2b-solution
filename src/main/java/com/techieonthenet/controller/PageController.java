package com.techieonthenet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * @author Shwetabh
 */
@Controller
public class PageController {

    private static Logger LOGGER = LoggerFactory.getLogger(PageController.class);

   @GetMapping("/main-page")
    public String pricingTables(Principal principal , Model model) {
       Authentication auth = (Authentication)principal;

       LOGGER.info("Inside Page Controller -  User Authorities {}" , auth.getAuthorities());
       return "product";
    }

}
