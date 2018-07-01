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
@Table(name = "AUTHORITY", 
    uniqueConstraints = { @UniqueConstraint(columnNames = "AUTHORITYNAME") })
@AttributeOverride(name = "id", column = @Column(name = "AUTHORITYID"))
public class Authority extends Persistent implements GrantedAuthority {

    private static final long serialVersionUID = -6546622267646062104L;
    
    
    @Size(min = 6, max = 100)
    @Column(name = "AUTHORITYCODE", unique = true, nullable = false, length = 100)
    private String authority;
    
    
    @Size(min = 6, max = 100)
    @Column(name = "AUTHORITYNAME", unique = true, nullable = false, length = 100)
    private String name;
    
    @ManyToMany(mappedBy="authorities")
    @ApiModelProperty(hidden=true)
    private Collection<Role> roles;

    public Authority() {
        super();
    }
    
    

}