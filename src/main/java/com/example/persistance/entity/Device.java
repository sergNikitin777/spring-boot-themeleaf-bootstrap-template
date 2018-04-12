package com.example.persistance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Device {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "device_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private DeviceStatus status;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceGroup deviceGroup;

    public Device() {

    }

    public Device(DeviceGroup deviceGroup, Address address, String name, DeviceStatus status) {
        this.name = name;
        this.status = status;
        this.address = address;
        this.deviceGroup = deviceGroup;
    }
}
