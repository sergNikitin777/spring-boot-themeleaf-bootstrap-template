package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Address extends Persistent {

    public Address(){

    }

    public Address(Integer id,String name, Float latitude, Float longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="address_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column
    private String name;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

}
