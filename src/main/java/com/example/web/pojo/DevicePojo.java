package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DevicePojo {
    private Integer statusId;
    private Integer addressId;
    private Integer deviceGroupId;
    private String name;
    private String description;
    private String mark;
    private String model;
    private Boolean heightAccess;
    private Boolean electricityAccess;
}
