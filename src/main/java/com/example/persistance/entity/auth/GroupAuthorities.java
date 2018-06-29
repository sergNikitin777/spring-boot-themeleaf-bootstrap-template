package com.example.persistance.entity.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class GroupAuthorities {

    @Column
    private UserGroup group;

    @Column(name = "role", nullable = false)
    private String role;
}
