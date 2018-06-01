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
public class CalendarTable {

    public CalendarTable() {
    }

    public CalendarTable(String adress, String token, String status) {
        this.adress = adress;
        this.token = token;
        this.status = status;
    }

    public CalendarTable(String adress, String token, String status, User user) {
        this.adress = adress;
        this.token = token;
        this.status = status;
        this.user = user;
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

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
