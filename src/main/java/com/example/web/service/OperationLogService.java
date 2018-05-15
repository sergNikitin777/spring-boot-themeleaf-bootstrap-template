package com.example.web.service;

import com.example.persistance.entity.Employee;
import com.example.persistance.entity.OperationLog;
import com.example.web.pojo.OperationLogPojo;

import java.util.List;

public interface OperationLogService {
    List<OperationLog> findAll();

    OperationLog findById(Long id);

    Integer addOperationLog(OperationLogPojo operationLog);

    void updateOperationLog(OperationLogPojo operationLog);

    void deleteOperationLog(Long id);

    void deleteAllOperationLog();
}
