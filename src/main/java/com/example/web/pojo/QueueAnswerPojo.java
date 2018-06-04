package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class QueueAnswerPojo {
    private String status;
    private List<InfoAnswerPojo> messages;
}
