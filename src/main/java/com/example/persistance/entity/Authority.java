package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

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
    private Collection<Role> roles;

    public Authority() {
        super();
    }
    
    @Override
    public String getAuthority() {
    	return authority;
    }
    
    @Override
    public String toString() {
    	return getAuthority();
    }

}