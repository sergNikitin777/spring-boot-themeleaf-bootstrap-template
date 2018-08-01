package com.example.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Отчеты
 */
@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Report {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "reports_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer number;
    
    @Column
    private String name;
    
}
