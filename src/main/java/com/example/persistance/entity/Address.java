package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Address implements Serializable {

    public Address() {

    }

    @Column
    private String municipality;

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "address_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String locality;

    public Address(Integer id, String municipality, String locality, String name, Float latitude, Float longitude) {
        this.id = id;
        this.municipality = municipality;
        this.locality = locality;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Column
    private String name;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

}
