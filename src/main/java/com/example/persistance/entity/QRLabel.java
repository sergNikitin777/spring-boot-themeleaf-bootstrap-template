package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * QR код
 */
@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class QRLabel {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="qrlabel_id_seq", allocationSize=1)
    //@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="id", unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String uid;

    @Column
    private Float latitude;

    @Column
    private Float longitude;
}
