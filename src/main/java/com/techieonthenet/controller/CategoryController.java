package com.techieonthenet.controller;

import com.techieonthenet.dto.CategoryDto;
import com.techieonthenet.entity.Category;
import com.techieonthenet.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * The type Category controller.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    private static final String CATEGORY = "category";
    @Autowired
    private CategoryService cs;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Add category string.
     *
     * @param model   the model
     * @param message the message
     * @return the string
     */
    @GetMapping( "/add"  )
    public String addCategory(Model model, @RequestParam( name = "message" , required = false) String message) {
        model.addAttribute(CATEGORY, new CategoryDto());
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "add-category";
    }

    /**
     * Add submitted category redirect view.
     *
     * @param categoryDto the category dto
     * @param model       the model
     * @param attributes  the attributes
     * @return the redirect view
     */
    @PostMapping("/add")
    public RedirectView addSubmittedCategory(@ModelAttribute CategoryDto categoryDto, Model model , RedirectAttributes attributes) {
        String message = "";
        try {
            Category category = new Category();
            category = modelMapper.map(categoryDto, Category.class);
            cs.save(category);
            model.addAttribute(CATEGORY, new CategoryDto());
            message = "Category has been successfully added";
        } catch (Exception e) {
            message = e.getMessage();
            model.addAttribute(CATEGORY, categoryDto);
        }
        attributes.addFlashAttribute("message" , message);
        return new RedirectView("/category/add");
    }

    /**
     * Find product by category string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/{id}/products")
    public String findProductByCategory(@PathVariable(name = "id") long id, Model model) {
        model.addAttribute("products", cs.findById(id).getProducts());
        return "products";
    }

    /**
     * Pricing tables string.
     *
     * @param principal the principal
     * @param model     the model
     * @param session   the session
     * @return the string
     */
    @GetMapping("/all")
    public String pricingTables(Principal principal, Model model, HttpSession session) {

        model.addAttribute("categories", cs.findAll());
        return "categories";
    }


}
