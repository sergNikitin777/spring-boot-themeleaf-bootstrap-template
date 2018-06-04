package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SmsPojo {
    private String clientId;
    private String phone;
    private String text;
    private String sender;
}
