package com.techieonthenet.controller;


import com.techieonthenet.dto.GroupDto;
import com.techieonthenet.entity.Address;
import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/add")
    public String addGroup(Model model) {
        model.addAttribute("group", new GroupDto());
        return "add-group";
    }

    @PostMapping("/add")
    public String addGroup(@ModelAttribute GroupDto groupDto, Model model) {
        try {

            Group group = convertGroupDtoToGroup(groupDto);
            groupService.save(group);
            model.addAttribute("group", new GroupDto());
            model.addAttribute("message", "Group has been successfully added");
        } catch (Exception e) {
            model.addAttribute("error", "Something went Wrong !! Please try again . Error Code  " + e.getLocalizedMessage());
            model.addAttribute("group", groupDto);
        }
        return "add-group";
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
        User adminUser = new User();
        adminUser.setEmail(groupDto.getAdminEmail());
        adminUser.setEnabled(true);
        adminUser.setFirstName(groupDto.getAdminFirstName());
        adminUser.setLastName(groupDto.getAdminLastName());
        adminUser.setUsername(groupDto.getAdminEmail());
        adminUser.setPasswordResetRequired(true);
        adminUser.setPassword(bCryptPasswordEncoder.encode("randomPasword")); //TODO random implementation
        adminUser.setGroup(group);
        Role adminRole = roleService.findByName("GROUP_ADMIN");
        adminUser.getRoles().add(adminRole);
        group.getUsers().add(adminUser);
        return group;
    }

}
