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

    private static final long serialVersionUID = -881988034564275210L;

    public Employee() {
    }

    public Employee(String fio, String position) {
        this.fio = fio;
        this.position = position;
        //this.role = role;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fio;

    @Column
    private String position;

    /*@Column
    private String role;*/

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Company company;
}
