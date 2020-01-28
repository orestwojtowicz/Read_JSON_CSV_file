package com.wojtowicz.file_reader.service;

import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;
import com.wojtowicz.file_reader.repository.EmployeeRepository;

import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeCSVRepository employeeCSVRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeCSVRepository employeeCSVRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCSVRepository = employeeCSVRepository;
    }



    /**
     * Method for finding employee entity based on jobName & calculate sum of earnings for given job
     * We could also make query to database like this:
     * @param jobName
     * */

    public double getSumOfEarningsFromJson(String jobName) {
        List<EmployeeJsonEntity> findJobs = employeeRepository.findAllByJob(jobName);
        double salary = 0;
        for (EmployeeJsonEntity emp : findJobs) {
            salary += emp.getSalary();
        }
       return salary;
    }


    public double getSumOfEarningsFromCsv(String jobName) {
        return 0.0;
    }






}











