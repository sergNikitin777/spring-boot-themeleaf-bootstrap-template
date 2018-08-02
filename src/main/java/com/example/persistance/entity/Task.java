package com.example.persistance.entity;

import com.example.persistance.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Task {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "task_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   /*@ManyToOne
    private Users user;

    @OneToMany
    private List<OperationGroup> operationGroupList = new ArrayList<>();*/

    @Column
    private String title;

    @Column
    private String desctiption;

    @Column
    private Status status;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @OneToMany
    private List<Device> devices = new ArrayList<>();

    @OneToOne(fetch=FetchType.EAGER)
    private Company customer;

    @OneToOne(fetch = FetchType.EAGER)
    private Company contractor;

    @Column
    private String checklist;
}
