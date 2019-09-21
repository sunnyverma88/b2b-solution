package com.techieonthenet.controller;

import com.techieonthenet.service.CategoryService;
import com.techieonthenet.service.ShoppingCartService;
import com.techieonthenet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author Shwetabh
 */
@Controller
public class PageController {

    private static Logger logger = LoggerFactory.getLogger(PageController.class);
    @Autowired
    private CategoryService cs;

    @Autowired
    private ShoppingCartService scs;

    @Autowired
    private UserService us;

    @GetMapping("/main-page")
    public String pricingTables(Principal principal, Model model, HttpSession session) {
        model.addAttribute("user", principal.getName());
        model.addAttribute("categories", cs.findAll());
        if ((scs.findByUserId(us.findByUsername(principal.getName()).getId()) != null))
            session.setAttribute("cartSize", scs.findByUserId(us.findByUsername(principal.getName()).getId()).getTotalItems());
        session.setAttribute("user", principal.getName());
        return "main-page";
    }

}
