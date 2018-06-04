package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
public class QueuePojo {
    private String login;
    private String password;
    private String scheduleTime;
    private String statusQueueName;
    private List<SmsPojo> messages;

}
