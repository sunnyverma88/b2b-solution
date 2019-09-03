package com.techieonthenet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author CrunchDroid
 */
@Controller
public class PageController {


   @GetMapping("/pricing-tables")
    public String pricingTables() {
        return "pricing-tables";
    }

}
