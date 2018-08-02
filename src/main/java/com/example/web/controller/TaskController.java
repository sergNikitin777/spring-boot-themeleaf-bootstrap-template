package com.example.web.controller;

import com.example.persistance.entity.Task;
import com.example.web.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @RequestMapping(value = "/admin/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTaskList() {
        log.debug("getTaskList");
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/task/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> tasks(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/task/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addTask(Task task) {
        log.debug("addTask");
        try {
            return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/task/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteTask(@PathVariable("id") Integer id) {
        log.debug("deleteTask");
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
