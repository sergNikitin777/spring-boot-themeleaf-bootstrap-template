package com.example.web.service;

import com.example.persistance.entity.Company;
import com.example.persistance.entity.Task;
import com.example.persistance.repository.CompanyRepository;
import com.example.persistance.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Integer id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Integer addTask(Task task) {
        return taskRepository.save(task).getId();
    }

    @Override
    public Task updateTask(Task task) {
        Task temp = new Task();
        if (task.getId()!=null){
            temp.setId(task.getId());
        }
        if (task.getTitle()!=null){
            temp.setStartDate(task.getStartDate());
        }
        if (task.getDesctiption()!=null){
            temp.setStartDate(task.getStartDate());
        }
        if (task.getStatus()!=null){
            temp.setStartDate(task.getStartDate());
        }
        if (task.getStartDate()!=null){
            temp.setStartDate(task.getStartDate());
        }
        if (task.getEndDate()!=null){
            temp.setStartDate(task.getStartDate());
        }
        if (task.getChecklist()!=null){
            temp.setStartDate(task.getStartDate());
        }
        if (task.getCustomer()!=null){
            if (task.getCustomer().getId()!=null){
                Company company = companyRepository.getOne(task.getCustomer().getId());
                temp.setCustomer(company);
            }
        }
        if (task.getContractor()!=null){
            if (task.getContractor().getId()!=null){
                Company company = companyRepository.getOne(task.getContractor().getId());
                temp.setContractor(company);
            }
        }
        return taskRepository.save(temp);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.delete(id);
    }
}
