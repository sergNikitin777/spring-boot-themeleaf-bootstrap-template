package com.example.persistance.entity.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class GroupAuthorities {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "group_auth_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private UserGroup group;

    @Column(name = "role", nullable = false)
    private String role;
}
