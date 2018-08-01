package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Company {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "сompany_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer employeeCount;

    @Column
    private Integer contractCount;

    @Column
    private String adress;

    @Column
    private String owner;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employee> employeeList = new ArrayList<>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }
}
