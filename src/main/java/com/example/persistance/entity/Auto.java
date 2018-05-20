package com.example.persistance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Auto {

    public Auto(){

    }

    public Auto(String mark, String model, String licensePlate, String type) {
        this.mark = mark;
        this.model = model;
        this.licensePlate = licensePlate;
        this.type = type;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "auto_driver_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "autoList")
    @JsonBackReference
    private List<AutoDriver> autoDrivers = new ArrayList<>();

    @Column
    private String mark;

    @Column
    private String model;

    @Column
    private String licensePlate;

    @Column
    private String type;

}
