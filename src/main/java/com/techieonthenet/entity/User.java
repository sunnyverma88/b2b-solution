package com.techieonthenet.entity;

import com.techieonthenet.entity.common.ApproverType;
import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="APP_USERS")
@Getter
@Setter
@SequenceGenerator(name = "app_user_generator", sequenceName = "app_user_seq", allocationSize = 1)
public class User extends Auditable implements UserDetails, Serializable {

    private static final long serialVersionUID = 902783495L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_generator")
    @Column(name = "Id", nullable = false, updatable = false)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private ApproverType approverType;


    private boolean passwordResetRequired = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private Collection<TaskItem> taskItems;

    private final List<String> getPrivileges() {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        roles.stream().map(Role::getPrivileges).forEach(collection::addAll);
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}

