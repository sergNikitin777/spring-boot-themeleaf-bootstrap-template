package com.example.persistance.entity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Contract {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "contract_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название
     */
    @Column
    private String name;
    
    /**
     * Описание
     */
    @Column
    private String description;
    
    /**
     * Заказчик
     */
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Company customer;
    
    /**
     * Исполнители
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Employee> contractorList = new HashSet<>();
    
    /**
     * Сотрудники
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Employee> employeeList = new HashSet<>();
    
    /**
     * Оборудование
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Device> deviceList = new HashSet<>();
    
    /**
     * Дата начала
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    
    /**
     * Дата завершения
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date closeDate;
    
    /**
     * Сумма контракта
     */
    @Column(name = "PRICE", precision = 19, scale = 4, columnDefinition="DECIMAL(19,4)") 
    private BigDecimal price;
    
    
    
}
