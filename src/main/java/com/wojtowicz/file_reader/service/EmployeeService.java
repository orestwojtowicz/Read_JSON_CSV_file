package com.wojtowicz.file_reader.service;

import com.wojtowicz.file_reader.domain.Employee;
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

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    public double getSumOfEarnings(String jobName) {
        List<Employee> findJobs = employeeRepository.findAllByJob(jobName);
        double salary = 0;
        for (Employee emp : findJobs) {
            salary += emp.getSalary();
        }
       return salary;
    }




}
