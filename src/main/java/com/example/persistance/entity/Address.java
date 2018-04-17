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
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Address parent;

    //@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    //private Set<Address> children = new HashSet<>();

    @Column
    private String name;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    /*
    public Set<Address> collectLeafChildren() {
        Set<Address> results = new HashSet<>();
        if (children.isEmpty()) {
            results.add(this);
        } else {
            children.forEach(child -> {
                results.addAll(child.collectLeafChildren());
            });
        }
        return results;
    }
    */

}
