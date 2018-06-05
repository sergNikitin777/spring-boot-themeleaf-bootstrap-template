package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class UserApplicationPojo {
    private Integer userId;
    private Integer autoId;
    private Integer autodriverId;
    private String adressFrom;
    private String adressTo;
    private Date date;
    private String phone;
    private String clientSurname;
    private String clientName;
    private String clientSecondName;
    private String driverSurname;
    private String driverName;
    private String driverSecondName;
    private Boolean smsToDriver;
    private Boolean smsToClient;
    private Integer goferCount;
    private Double price;
    private String description;
}
