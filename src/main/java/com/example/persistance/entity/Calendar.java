package com.example.persistance.entity;

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
public class Calendar {

    public Calendar() {
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "calendar_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String adress;

    @Column
    private String token;
}
