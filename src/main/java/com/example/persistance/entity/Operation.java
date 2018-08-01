package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Операция в рамках Task (задачи)
 *
 */
@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Operation implements Serializable {

    private static final long serialVersionUID = -1711873713439276552L;

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "operation_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;
}
