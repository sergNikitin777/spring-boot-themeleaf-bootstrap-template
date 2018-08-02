package com.example.persistance.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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
    @OneToOne(fetch = FetchType.EAGER)
    private Company customer;
    
    /**
     * Исполнитель
     */
    @OneToOne(fetch = FetchType.EAGER)
    private Company company;
    
    /**
     * Оборудование
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Device> OneToMany = new HashSet<>();
    
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
