package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class CalendarDeleteVeventReqPojo {
    private String caldavHost = "caldav.yandex.ru";
    private Integer caldavPort = 443;
    private String protocol = "https";
    private String userName = "logistikatest@yandex.ru";
    private String password = "kjubcn123456";
    private String calPrefix = "/calendars/";
    private String calPostfix = "/events-5825759";

    private String uid;

}
