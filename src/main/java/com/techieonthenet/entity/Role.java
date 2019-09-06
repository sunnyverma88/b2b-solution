package com.techieonthenet.entity;


import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "App_Role")
@Getter
@Setter
public class Role extends Auditable implements Serializable{

    private static final long serialVersionUID = 890245234L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_role_generator")
    @SequenceGenerator(name = "app_role_generator", sequenceName = "app_role_seq", allocationSize = 50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Role(){}

}