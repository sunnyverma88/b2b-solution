package com.techieonthenet.entity;


import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * The type Role.
 */
@Entity
@Table(name = "App_Roles")
@Getter
@Setter
@SequenceGenerator(name = "app_role_generator", sequenceName = "app_role_seq", allocationSize = 1)
public class Role extends Auditable implements Serializable{

    private static final long serialVersionUID = 890245234L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_role_generator")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "priv_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges = new HashSet<>();

}