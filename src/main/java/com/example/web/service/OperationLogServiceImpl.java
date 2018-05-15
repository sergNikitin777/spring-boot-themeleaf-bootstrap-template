package com.example.web.service;

import com.example.persistance.entity.Device;
import com.example.persistance.entity.Employee;
import com.example.persistance.entity.Operation;
import com.example.persistance.entity.OperationLog;
import com.example.persistance.repository.DeviceRepository;
import com.example.persistance.repository.EmployeeRepository;
import com.example.persistance.repository.OperationLogRepository;
import com.example.persistance.repository.OperationRepository;
import com.example.web.pojo.OperationLogPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationLogServiceImpl implements OperationLogService {
    private final OperationRepository operationRepository;
    private final EmployeeRepository employeeRepository;
    private final DeviceRepository deviceRepository;
    private final OperationLogRepository operationLogRepository;

    @Override
    public List<OperationLog> findAll() { return operationLogRepository.findAll(); }

    @Override
    public OperationLog findById(Long id) { return operationLogRepository.findOne(id); }

    @Override
    public Integer addOperationLog(OperationLogPojo operationLog) {
        Operation operation = operationRepository.findOne(operationLog.getOperationId());
        Employee employee = employeeRepository.findOne(operationLog.getEmployeeId());
        Device device = deviceRepository.findOne(operationLog.getDeviceId());

        return operationLogRepository.save(new OperationLog(operation, employee, device, operationLog.getCreationDate(), operationLog.getStatus())).getId();
    }

    @Override
    public void updateOperationLog(OperationLogPojo operationLog) {

    }

    @Override
    public void deleteOperationLog(Long id) { operationLogRepository.delete(id); }

    @Override
    public void deleteAllOperationLog() { operationLogRepository.deleteAll(); }
}
