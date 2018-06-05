package com.example.persistance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
public class UserApplication {
    public UserApplication(){
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "user_application_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public UserApplication(Integer userId, Integer autoId, Integer autodriverId, String adressFrom, String adressTo, Date date, String phone, String clientSurname, String clientName, String clientSecondName, String driverSurname, String driverName, String driverSecondName, Boolean smsToDriver, Boolean smsToClient, Integer goferCount, Double price, String description) {
        this.userId = userId;
        this.autoId = autoId;
        this.autodriverId = autodriverId;
        this.adressFrom = adressFrom;
        this.adressTo = adressTo;
        this.date = date;
        this.phone = phone;
        this.clientSurname = clientSurname;
        this.clientName = clientName;
        this.clientSecondName = clientSecondName;
        this.driverSurname = driverSurname;
        this.driverName = driverName;
        this.driverSecondName = driverSecondName;
        this.smsToDriver = smsToDriver;
        this.smsToClient = smsToClient;
        this.goferCount = goferCount;
        this.price = price;
        this.description = description;
    }

    /* @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "auto_id")
    @JsonBackReference
    private Auto auto;

    @ManyToMany(mappedBy = "autodriver_id")
    @JsonBackReference
    private AutoDriver autoDriver;*/

    @Column
    private Integer userId;//дичь

    @Column
    private Integer autoId;//дичь

    @Column
    private Integer autodriverId;//дичь

    @Column
    private String adressFrom;

    @Column
    private String adressTo;

    @Column
    private Date date;

    @Column
    private String phone;

    @Column
    private String clientSurname;

    @Column
    private String clientName;

    @Column
    private String clientSecondName;

    @Column
    private String driverSurname;

    @Column
    private String driverName;

    @Column
    private String driverSecondName;

    @Column
    private Boolean smsToDriver;

    @Column
    private Boolean smsToClient;

    @Column
    private Integer goferCount;

    @Column
    private Double price;

    @Column
    private String description;
}
