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
public class UserGroupMembers {
    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "group_members_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private User user;

    @Column
    private UserGroup group;
}
