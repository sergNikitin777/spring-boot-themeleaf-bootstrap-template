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


    public Device() {

    }

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

    @Column
    private String descripion;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(fetch = FetchType.EAGER)
    private Ekt ekt;

    @Column
    private String manufacturer;

    @Column
    private String model;

    @Column
    private String productionYear;

    @Column
    private String accessNeeded;

    @Column
    private String maintence;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceGroup deviceGroup;

    @Column
    private Boolean heightAccess;

    @Column
    private Boolean electricityAccess;

    public Device(DeviceGroup deviceGroup, Address address,  String name, String descripion,  String model, DeviceStatus status, Boolean heightAccess, Boolean electricityAccess, Ekt ekt, String manufacturer,  String productionYear, String accessNeeded, String maintence) {
        this.status = status;
        this.name = name;
        this.address = address;
        this.ekt = ekt;
        this.manufacturer = manufacturer;
        this.model = model;
        this.productionYear = productionYear;
        this.accessNeeded = accessNeeded;
        this.maintence = maintence;
        this.deviceGroup = deviceGroup;
        this.heightAccess = heightAccess;
        this.electricityAccess = electricityAccess;
        this.descripion = descripion;
    }
}
