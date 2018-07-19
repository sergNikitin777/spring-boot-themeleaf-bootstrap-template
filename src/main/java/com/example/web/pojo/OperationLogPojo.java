package com.example.web.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class OperationLogPojo {
    private Integer operationId;
    private Integer employeeId;
    private Integer deviceId;
    private Date creationDate;
    private String status;
}
