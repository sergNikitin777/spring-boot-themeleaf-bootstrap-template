package com.example.web.service;

import com.example.persistance.entity.Report;
import com.example.persistance.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report findById(Integer id) {
        return reportRepository.findOne(id);
    }

    @Override
    public Integer addReport(Report report) {
        return reportRepository.save(report).getId();
    }

    @Override
    public Report updateReport(Report report) {
        Report temp = new Report();
        if (report.getId()!=null){
            temp.setId(report.getId());
        }
        if (report.getName()!=null){
            temp.setName(report.getName());
        }
        if (report.getNumber()!=null){
            temp.setNumber(report.getNumber());
        }
        return reportRepository.save(temp);
    }

    @Override
    public void deleteReport(Integer id) {
        reportRepository.delete(id);
    }

}
