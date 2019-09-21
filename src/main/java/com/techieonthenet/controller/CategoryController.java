package com.techieonthenet.controller;

import com.techieonthenet.entity.Category;
import com.techieonthenet.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static final String CATEGORY = "category";
    @Autowired
    private CategoryService cs;

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute(CATEGORY, new Category());
        return "add-category";
    }

    @PostMapping("/add")
    public String addSubmittedCategory(@ModelAttribute Category category, Model model) {
        try {
            cs.save(category);
            model.addAttribute(CATEGORY, new Category());
            model.addAttribute("message", "Category has been successfully added");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute(CATEGORY, category);
        }

        return "add-category";
    }

    @GetMapping("/{id}/products")
    public String findProductByCategory(@PathVariable(name = "id") long id, Model model) {

        model.addAttribute("products", cs.findById(id).getProducts());
        return "products";
    }


}
