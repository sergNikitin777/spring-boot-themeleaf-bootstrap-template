package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Device extends Persistent {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="device_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    @Column
    private String name;
}
