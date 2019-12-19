package com.techieonthenet.controller;


import com.techieonthenet.dto.GroupDto;
import com.techieonthenet.entity.Address;
import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.common.AddressType;
import com.techieonthenet.entity.common.GroupType;
import com.techieonthenet.service.GroupService;
import com.techieonthenet.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The type Group controller.
 */
@RequestMapping("/group")
@Controller
public class GroupController {

    private static Logger logger = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private GroupService groupService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Add group string.
     *
     * @param model   the model
     * @param message the message
     * @return the string
     */
    @GetMapping("/add")
    public String addGroup(Model model , @RequestParam(name = "message" , required = false) String message) {
        model.addAttribute("group", new GroupDto());
        model.addAttribute("message", message);
        return "add-group";
    }

    /**
     * Add group redirect view.
     *
     * @param groupDto           the group dto
     * @param redirectAttributes the redirect attributes
     * @return the redirect view
     */
    @PostMapping("/add")
    public RedirectView addGroup(@ModelAttribute GroupDto groupDto, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            Group group = convertGroupDtoToGroup(groupDto);
            if(groupService.findByGstNo(group.getGstNo()) == null){
                groupService.save(group);
                message="Group has been successfully added";} else { message="Group with provided GST No already exists";}
        } catch (Exception e) {
            message ="Something went Wrong !! Please try again . Error Code  " + e.getLocalizedMessage();
        }
        redirectAttributes.addAttribute("message"  , message);
        return new RedirectView("/group/add");
    }

    private Group convertGroupDtoToGroup(GroupDto groupDto) {

        Group group = new Group();
        Address address = new Address();
        address.setName(groupDto.getName());
        address.setAddressline1(groupDto.getAddressline1());
        address.setAddressline2(groupDto.getAddressline2());
        address.setLandmark(groupDto.getLandmark());
        address.setZipcode(groupDto.getZipcode());
        address.setCity(groupDto.getCity());
        address.setState(groupDto.getState());
        address.setType(AddressType.BILLING_ADDRESS);

        group.setAddress(address);
        group.setName(groupDto.getName());
        group.setApprovalThreshold(groupDto.getApprovalThreshold());
        group.setGstNo(groupDto.getGstNo());
        group.setProfitPercentage(groupDto.getProfitPercentage());
        group.setType(GroupType.valueOf(groupDto.getType()));
        return group;
    }

}
