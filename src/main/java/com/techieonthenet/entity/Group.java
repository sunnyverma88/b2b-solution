package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import com.techieonthenet.entity.common.GroupType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Group.
 */
@Entity
@Getter
@Setter
@Table(name = "groups")
@SequenceGenerator(name = "group_generator", sequenceName = "group_seq", allocationSize = 1)
public class Group extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_generator")
    private Long id;
    private String name;
    private String website;

    @Enumerated(EnumType.STRING)
    private GroupType type;
    private String gstNo;
    private BigDecimal profitPercentage;

    private BigDecimal approvalThreshold;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
