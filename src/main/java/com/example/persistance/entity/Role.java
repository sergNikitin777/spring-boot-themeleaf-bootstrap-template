package com.example.persistance.entity;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ADMROLE", 
    uniqueConstraints = { @UniqueConstraint(columnNames = "ROLENAME") })
@AttributeOverride(name = "id", column = @Column(name = "ADMROLEID"))
public class Role extends Persistent {

    private static final long serialVersionUID = -6546622267646062104L;
    
    
    @Size(min = 6, max = 100)
    @Column(name = "ROLECODE", unique = true, nullable = false, length = 100)
    private String role;
    
    @Size(min = 6, max = 100)
    @Column(name = "ROLENAME", unique = true, nullable = false, length = 100)
    private String name;
    
    @ManyToMany(mappedBy="roles")
    private Collection<User> users;
    
    
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @ManyToMany(cascade = { 
        CascadeType.ALL
    },
        mappedBy="roles",
        fetch = FetchType.EAGER
    )
    @JoinTable(name = "ADMROLE_AUTHORITY")
    @Column(name = "AUTHORITY", nullable = false)
    @ApiModelProperty(hidden=true)
    private Collection<Authority> authorities;
    
    
    public Role() {
        super();
    }
    
    @Override
    public String toString() {
    	return getRole();
    }

}