package com.techieonthenet.controller;

import com.techieonthenet.dto.ProductDto;
import com.techieonthenet.entity.Image;
import com.techieonthenet.entity.Product;
import com.techieonthenet.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private CategoryService cs;
    @Autowired
    private UserService us;
    @Autowired
    private ProductService productService;
    @Autowired
    private HsnCodeGstMappingService hsnService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ShoppingCartService scs;

    @GetMapping("/add")
    public String addProduct(Model model, Principal principal, HttpSession session) {

        model.addAttribute("categories", cs.findAll());
        model.addAttribute("product", new ProductDto());
        model.addAttribute("hsnCodes", hsnService.findAll());
        return "add-product";
    }

    @PostMapping("/add")
    public String addSubmittedProduct(@ModelAttribute ProductDto productDto, Model model) {
        logger.info("Adding Product  - Name - {}", productDto.getName());

        try {
            productService.save(convertProductDtoToProduct(productDto));
            model.addAttribute("product", new ProductDto());
            model.addAttribute("message", "Product has been successfully added");
        } catch (Exception e) {
            model.addAttribute("error", "Something went Wrong !! Please try again . Error Code  " + e.getLocalizedMessage());
            model.addAttribute("product", productDto);
        }
        model.addAttribute("hsnCodes", hsnService.findAll());
        model.addAttribute("categories", cs.findAll());
        return "add-product";
    }

    @GetMapping("/all")
    public String findAllProducts(Model model) {

        model.addAttribute("categories", cs.findAll());
        return "products";
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setActualPrice(productDto.getActualPrice());
        product.setMrpPrice(productDto.getMrpPrice());
        product.setCategory(cs.findById(productDto.getCategoryId()));
        product.setDescription(productDto.getDescription());
        product.setSpecifications(productDto.getSpecifications());
        product.setShippingDays(productDto.getShippingDays());
        product.setHsnCode(productDto.getHsnCode());
        Image img = new Image(productDto.getImgUrl());
        img.setProduct(product);
        List<Image> imageList = new ArrayList<>();
        imageList.add(img);
        product.setImgList(imageList);
        return product;
    }

}
