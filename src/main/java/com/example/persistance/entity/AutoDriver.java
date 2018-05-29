package com.example.persistance.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class AutoDriver {

    public AutoDriver(){

    }

    public AutoDriver(List<Auto> autoList, String firstName, String surname, String patronymic, String phoneNumber) {
        this.autoList = autoList;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "auto_driver_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "auto_driver_link" , joinColumns = @JoinColumn(name = "auto_driver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auto_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<Auto> autoList = new ArrayList<>();

    @Column
    private String firstName;

    @Column
    private String surname;

    @Column
    private String patronymic;

    @Column
    private  String phoneNumber;
}
