package com.example.persistance.entity;

import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ADMUSER",
    uniqueConstraints = { 
        @UniqueConstraint(columnNames = "EMAIL"),
        @UniqueConstraint(columnNames = "USERNAME") })
@AttributeOverride(name = "id", column = @Column(name = "ADMUSERID"))
public class User extends Persistent implements UserDetails {

    private static final long serialVersionUID = -6546622267646062104L;
    
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z0-9]*$", message = "Only small letters and numbers allowed")
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    @Size(min = 6, max = 100)
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Size(min = 6, max = 50)
    @Column(name = "USERPASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "DATECREATE", nullable = false)
    private Date datecreate;
    
    @Column(name = "DATEBLOCK", nullable = true)
    private Date dateblock;
    
    @Column(name = "ISBLOCKED", nullable = false)
    private int isblocked = 0;
    
    @Size(max = 100)
    @Column(name = "ESP", unique = false, nullable = false, length = 100)
    private String esp;

    @NotEmpty
    @ElementCollection(targetClass = Role.class)
    @ManyToMany(cascade = { 
        CascadeType.ALL,
        CascadeType.PERSIST, 
        CascadeType.MERGE
    },
        mappedBy="users",
        fetch = FetchType.EAGER
    )
    @JoinTable(name = "ADMUSERROLE")
    @Column(name = "ROLE", nullable = false)
    @ApiModelProperty(hidden=true)
    private Collection<com.example.persistance.entity.Role> roles;

    //@OneToMany
    //private List<Task> taskList = new ArrayList<>();

    public User()
    {
        super();
    }
    
    transient private String salt = "";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> autorities = getRoles().stream()
                .map(Role::getAuthorities)
                .flatMap(ii -> ii.stream())
                .collect(toSet());
        log.info("{}", autorities);
        return autorities;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true; // Not Implemented
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true; // Not Implemented
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return isblocked == 0;
    }
    
    
    @Override
    public String toString() {
    	return getUsername();
    }


}
