package com.example.persistance.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


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

    @Column
    private String name;

    @Column
    private Integer status;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date closeDate;

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Customer customer;
}
