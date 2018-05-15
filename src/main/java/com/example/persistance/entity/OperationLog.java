package com.example.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 2882267245170513193L;

    public OperationLog() {

    }

    public OperationLog(Operation operation, Employee employee, Device device, Date creationDate, String status) {
        this.operation = operation;
        this.employee = employee;
        this.device = device;
        this.creationDate = creationDate;
        this.status = status;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "operation_log_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    private Operation operation;

    @ManyToOne()
    private Employee employee;

    @ManyToOne()
    private Device device;

    @Column
    private Date creationDate;

    @Column
    private String status;
}
