package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Address implements Serializable {

    public Address() {

    }

    public Address(Address parent, String name, Float latitude, Float longitude) {
        this.parent = parent;

        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "address_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Address parent;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private String mark;

    @Column
    private String model;

    @Column
    private String type;

    @Column
    private String accsess;
    
}
