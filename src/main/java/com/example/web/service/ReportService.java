package com.example.web.service;


import com.example.persistance.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> findAll();

    Report findById(Integer id);

    Integer addReport(Report customer);

    Report updateReport(Report customer);

    void deleteReport(Integer id);
}
