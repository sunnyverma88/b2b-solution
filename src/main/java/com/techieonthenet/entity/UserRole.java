package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type User role.
 */
@Entity
@Table(name="users_roles")
@Getter
@Setter
@SequenceGenerator(name = "user_role_generator", sequenceName = "user_role_seq", allocationSize = 1)
public class UserRole extends Auditable implements Serializable {
    private static final long serialVersionUID = 890345L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_generator")
    private long userRoleId;

    /**
     * Instantiates a new User role.
     */
    public UserRole () {}

    /**
     * Instantiates a new User role.
     *
     * @param user the user
     * @param role the role
     */
    public UserRole (User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


}