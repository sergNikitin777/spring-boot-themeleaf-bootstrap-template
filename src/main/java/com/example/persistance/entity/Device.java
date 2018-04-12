package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Data
public class Device {

    public Device() {

    }

    public Device(Integer addressId, String name, Float latitude, Float longitude, Integer status) {
        this.addressId = addressId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "device_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column
    private Integer addressId;

    @Column
    private String name;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private Integer status;
}
