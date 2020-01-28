package com.wojtowicz.file_reader.controller;

import com.wojtowicz.file_reader.repository.EmployeeRepository;
import com.wojtowicz.file_reader.service.EmployeeService;
import com.wojtowicz.file_reader.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@RestController
@RequestMapping("/employees")
public class EmployeesController {


    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{job}/salaries")
    public double getSingleSalary(@PathVariable String job) {
        return employeeService.getSumOfEarnings(job);

    }





}
