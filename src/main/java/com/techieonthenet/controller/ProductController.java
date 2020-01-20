package com.techieonthenet.controller;

import com.techieonthenet.dto.ProductDto;
import com.techieonthenet.entity.Image;
import com.techieonthenet.entity.Product;
import com.techieonthenet.exception.UserDefinedException;
import com.techieonthenet.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.MathContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Product controller.
 */
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

    @Autowired
    private HsnCodeGstMappingService hsnCodeGstMappingService;

    /**
     * Add product string.
     *
     * @param model     the model
     * @param principal the principal
     * @param session   the session
     * @return the string
     */
    @GetMapping("/add")
    public String addProduct(Model model, Principal principal, HttpSession session) {

        model.addAttribute("categories", cs.findAll());
        model.addAttribute("product", new ProductDto());
        model.addAttribute("hsnCodes", hsnService.findAll());
        return "add-product";
    }

    /**
     * Add submitted product string.
     *
     * @param productDto the product dto
     * @param model      the model
     * @return the string
     */
    @PostMapping("/add")
    public String addSubmittedProduct(@ModelAttribute ProductDto productDto, Model model) {
        logger.info("Adding Product  - Name - {}", productDto.getName());

        try {
            if (productDto.getMrpPrice().subtract(productDto.getSellingPrice()).intValue() > 0) {
                throw new UserDefinedException(UserDefinedException.MRP_GREATER_THAN_SELLING_PRICE);
            }
            productService.save(convertProductDtoToProduct(productDto));
            model.addAttribute("product", new ProductDto());
            model.addAttribute("message", "Product has been successfully added");
        } catch (Exception e) {
            model.addAttribute("message", "Something went Wrong !! Please try again . Error Code  :" + e.getLocalizedMessage());
            model.addAttribute("product", productDto);
        }
        model.addAttribute("hsnCodes", hsnService.findAll());
        model.addAttribute("categories", cs.findAll());
        return "add-product";
    }

    /**
     * Edit submitted product redirect view.
     *
     * @param productDto         the product dto
     * @param model              the model
     * @param redirectAttributes the redirect attributes
     * @return the redirect view
     */
    @PostMapping("/edit")
    public RedirectView editSubmittedProduct(@ModelAttribute ProductDto productDto, Model model, RedirectAttributes redirectAttributes) {
        logger.info("Adding Product  - Name - {}", productDto.getName());
        String message = "";
        try {
            if (productDto.getMrpPrice().subtract(productDto.getSellingPrice()).intValue() > 0) {
                throw new UserDefinedException(UserDefinedException.MRP_GREATER_THAN_SELLING_PRICE);
            }
            productService.save(convertProductDtoToProduct(productDto));
            model.addAttribute("product", productDto);
            message = "Product has been successfully updated";
        } catch (Exception e) {
            message = "Something went Wrong !! Please try again . Error Code  :" + e.getLocalizedMessage();
            model.addAttribute("product", productDto);
        }
        model.addAttribute("hsnCodes", hsnService.findAll());
        model.addAttribute("categories", cs.findAll());
        redirectAttributes.addFlashAttribute("message", message);
        String redirectUrl="/product/"+productDto.getId()+"/details";
        return new RedirectView(redirectUrl);

    }

    /**
     * Find all products string.
     *
     * @param model the model
     * @return the string
     */
    @Cacheable("products")
    @GetMapping("/all")
    public String findAllProducts(Model model) {
        model.addAttribute("categories", cs.findAll());
        return "products";
    }

    /**
     * Enable disable product redirect view.
     *
     * @param model     the model
     * @param productId the product id
     * @param action    the action
     * @return the redirect view
     */
    @GetMapping("/{productId}/{action}")
    public RedirectView enableDisableProduct(Model model, @PathVariable Long productId, @PathVariable String action) {
        Product product = productService.findById(productId);
        if (action.equals("enable")) {
            product.setVisible(Boolean.TRUE);
        } else if (action.equals("disable")) {
            product.setVisible(Boolean.FALSE);
        }
        productService.save(product);
        return new RedirectView("/product/admin/all");
    }


    /**
     * Gets all products for admin.
     *
     * @param model   the model
     * @param message the message
     * @return the all products for admin
     */
    @GetMapping("/admin/all")
    public String getAllProductsForAdmin(Model model, @RequestParam(name = "message", required = false) String message) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("categories", cs.findAll());
        return "product-list";
    }

    /**
     * Gets order details.
     *
     * @param model     the model
     * @param principal the principal
     * @param productId the product id
     * @return the order details
     */
    @GetMapping("/{productId}/details")
    public String getOrderDetails(Model model, Principal principal, @PathVariable Long productId) {
        ProductDto productDto = convertProductToProductDto(productService.findById(productId));
        model.addAttribute("product", productDto);
        model.addAttribute("hsnCodes", hsnService.findAll());
        model.addAttribute("categories", cs.findAll());
        return "edit-product";
    }


    private Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) {
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setMrpPrice(productDto.getMrpPrice());
        product.setCategory(cs.findById(productDto.getCategoryId()));
        product.setDescription(productDto.getDescription());
        product.setSpecifications(productDto.getSpecifications());
        product.setShippingDays(productDto.getShippingDays());
        product.setHsnCode(productDto.getHsnCode());
        BigDecimal gst = new BigDecimal(0);
        gst = gst.add(new BigDecimal(hsnCodeGstMappingService.findByHsnCode(
                product.getHsnCode()).getGstPercentage()).
                multiply(product.getSellingPrice())).divide(new BigDecimal(100).round(MathContext.DECIMAL32));
        product.setPriceWithoutGst(product.getSellingPrice().subtract(gst));
        product.setGst(gst);

        Image img = new Image(productDto.getImgUrl());
        img.setProduct(product);
        List<Image> imageList = new ArrayList<>();
        imageList.add(img);
        product.setImgList(imageList);
        return product;
    }

    private ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setBrand(product.getBrand());
        productDto.setSellingPrice(product.getSellingPrice());
        productDto.setMrpPrice(product.getMrpPrice());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setDescription(product.getDescription());
        productDto.setSpecifications(product.getSpecifications());
        productDto.setShippingDays(product.getShippingDays());
        productDto.setHsnCode(product.getHsnCode());
        productDto.setVisible(product.isVisible());
        productDto.setImgUrl(product.getImgList().get(0).getImgUrl());
        return productDto;
    }

}
