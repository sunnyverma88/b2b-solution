package com.techieonthenet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_role")
@Getter
@Setter
public class UserRole implements Serializable {
    private static final long serialVersionUID = 890345L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_generator")
    @SequenceGenerator(name = "user_role_generator", sequenceName = "user_role_seq", allocationSize = 50)
    @Column(name="user_role_id", nullable=false, updatable = false)
    private long userRoleId;

    public UserRole () {}

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