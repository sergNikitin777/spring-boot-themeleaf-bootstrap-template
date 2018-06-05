package com.example.persistance.entity;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.persistance.enums.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ADM_USER", uniqueConstraints = { @UniqueConstraint(columnNames = "EMAIL"),
        @UniqueConstraint(columnNames = "USERNAME") })
public class User extends Persistent implements UserDetails
{
    public User(String email, String username, String password, String yandexToken, Date dateYandexToken, Boolean smsToDriver, Boolean smsToClient, Integer notiftime, String region, String phone, Integer requestTransferInterval, String companyName,Collection<Role> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.yandexToken = yandexToken;
        this.dateYandexToken = dateYandexToken;
        this.smsToDriver = smsToDriver;
        this.smsToClient = smsToClient;
        this.notiftime = notiftime;
        this.region = region;
        this.phone = phone;
        this.requestTransferInterval = requestTransferInterval;
        this.CompanyName = companyName;
        this.roles = roles;
    }

    private static final long serialVersionUID = 1L;

    @Size(min = 6, max = 100)
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z0-9]*$", message = "Only small letters and numbers allowed")
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    @Size(min = 6, max = 50)
    @Column(name = "PWD", nullable = false, length = 50)
    private String password;

    @Column(name = "isENABLED", nullable = false)
    private boolean enabled;

    @Column(name = "isLOCKED", nullable = false)
    private boolean locked = false;

    @Column
    private String yandexToken;

    public Date getDateYandexToken() {
        return dateYandexToken;
    }

    public void setDateYandexToken(Date dateYandexToken) {
        this.dateYandexToken = dateYandexToken;
    }

    @Column
    private Date dateYandexToken;

    @Column
    private Boolean smsToDriver;

    @Column
    private Boolean smsToClient;

    @Column
    private Integer notiftime;

    @Column
    private String region;

    @Column
    private String phone;

    @Column
    private Integer requestTransferInterval;

    @Column
    private String CompanyName;

    @NotEmpty
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private Collection<Role> roles;

    public User()
    {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return getRoles().stream().map(Role::getAuthority)
                .map(SimpleGrantedAuthority::new).collect(toSet());
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CalendarTable> calendars;

//    @OneToMany(mappedBy = "userApplications", cascade = CascadeType.ALL)
//    private Set<UserApplication> userApplications;
}
