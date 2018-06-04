package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InfoAnswerPojo {
    private String clientId;
    private String smscId;
    private String status;
}
