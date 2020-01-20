package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "app_privileges")
@SequenceGenerator(name = "app_priv_generator", sequenceName = "app_priv_seq", allocationSize = 1)
public class Privilege extends Auditable implements Serializable {

    private static final long serialVersionUID = 890345L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_priv_generator")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges" , fetch = FetchType.EAGER)
    private Collection<Role> roles = new HashSet<>();

    public Privilege() {
        super();

    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }

}