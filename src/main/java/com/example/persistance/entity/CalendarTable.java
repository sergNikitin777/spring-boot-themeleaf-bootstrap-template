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

    public CalendarTable(String adress, String token, String status, String port, String userName, String password, String prefix, String postfix) {
        this.adress = adress;
        this.token = token;
        this.status = status;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public CalendarTable(String adress, String token, String status) {
        this.adress = adress;
        this.token = token;
        this.status = status;
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

    @Column
    private String port;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String prefix;

    @Column
    private String postfix;
}
