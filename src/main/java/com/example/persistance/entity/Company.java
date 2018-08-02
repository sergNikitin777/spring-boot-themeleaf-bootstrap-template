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
    private String address;

    @Column
    private String director;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employee> employeeList = new ArrayList<>();

    public Company() {
    }

    public Company(String name, Integer employeeCount, Integer contractCount, String address, String director, String phone, String email) {
        this.name = name;
        this.employeeCount = employeeCount;
        this.contractCount = contractCount;
        this.address = address;
        this.director = director;
        this.phone = phone;
        this.email = email;
    }
}
