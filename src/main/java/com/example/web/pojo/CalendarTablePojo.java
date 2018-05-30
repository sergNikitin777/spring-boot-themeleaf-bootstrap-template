package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CalendarTablePojo {
    private String adress;
    private String token;
    private String status;
    private Integer userId;
}
