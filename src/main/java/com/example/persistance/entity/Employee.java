package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Employee implements Serializable {

    public Employee() {
    }

    public Employee(String surname, String name, String patronymic, String position) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.position = position;
        //this.role = role;

    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private String patronymic;

    @Column
    private String position;

    /*@Column
    private String role;*/

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Company company;
}
