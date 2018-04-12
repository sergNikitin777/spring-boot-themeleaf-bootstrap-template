package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class OperationLog {
    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "operation_log_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    private Operation operation;

    @ManyToOne()
    private User user;

    @Column
    private LocalDateTime creationDate;

    @Column
    private String status;
}
