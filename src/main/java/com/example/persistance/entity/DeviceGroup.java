package com.example.persistance.entity;

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
public class DeviceGroup {
    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "device_gr_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "deviceGroup")
    private List<Device> deviceList = new ArrayList<>();

    public DeviceGroup() {
    }

    public DeviceGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
