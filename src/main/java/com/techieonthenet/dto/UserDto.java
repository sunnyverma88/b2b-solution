package com.techieonthenet.dto;

import com.techieonthenet.entity.common.ApproverType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type User dto.
 */
@Getter
@Setter
public class UserDto implements Serializable {

    private static final long serialVersionUID = 890345L;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private boolean enabled = true;
    private ApproverType approverType;

    private boolean passwordResetRequired = false;
    private Long groupId;
    private String roleName;
}
