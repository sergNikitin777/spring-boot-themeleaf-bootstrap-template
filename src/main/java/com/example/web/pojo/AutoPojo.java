package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AutoPojo {
    private Integer driverId;
    private String mark;
    private String model;
    private String licensePlate;
    private String type;
}
