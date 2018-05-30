package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class UserPojo {
    private String email;
    private String username;
    private String password;
    private String yandexToken;
    private Date dateYandexToken;
    private Boolean smsToDriver;
    private Boolean smsToClient;
    private Integer  notiftime;
    private String region;
    private String phone;
    private Integer requestTransferInterval;
    private String companyName;

    public Date getDateYandexToken() {
        return dateYandexToken;
    }
}
