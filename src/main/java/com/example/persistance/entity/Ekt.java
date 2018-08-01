package com.example.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * ЭКТ
 */
@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Ekt {


    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "reports_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Device device;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
    
    @Column
    private String number;
    
}
