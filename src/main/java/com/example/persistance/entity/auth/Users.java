package com.example.persistance.entity.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.persistence.*;
import com.example.persistance.entity.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@Table(name="users")
public class Users implements UserDetails
{

    private static final long serialVersionUID = -6546622267646062104L;

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;

    @Column
    private boolean locked = false;

//    @NotEmpty
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role", nullable = false)
//    private Collection<Role> roles;

    @OneToMany
    private List<Task> taskList = new ArrayList<>();

    public Users()
    {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //return getRoles().stream().map(Role::getAuthority)
        //    .map(SimpleGrantedAuthority::new).collect(toSet());
        return  null;
    }


    @Override
    public boolean isAccountNonExpired()
    {
        return true; // Not Implemented
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true; // Not Implemented
    }

}
