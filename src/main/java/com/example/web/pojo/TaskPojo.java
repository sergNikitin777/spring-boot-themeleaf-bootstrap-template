package com.example.web.pojo;

import com.example.persistance.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class TaskPojo {
    private String title;
    private String desctiption;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status checklist;
    private Integer customerId;
    private Integer contractorId;
}