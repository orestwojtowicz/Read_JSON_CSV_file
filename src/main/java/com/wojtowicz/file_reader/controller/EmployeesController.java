package com.wojtowicz.file_reader.controller;

import com.wojtowicz.file_reader.repository.EmployeeRepository;
import com.wojtowicz.file_reader.service.EmployeeService;
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


    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;


    public EmployeesController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("v1/{job}/salaries/json")
    public double getSingleSalaryFromJsonFile(@PathVariable String job) {
        return employeeService.getSumOfEarningsFromJson(job);

    }


    @GetMapping("v2/{job}/salaries/csv")
    public double getSingleSalaryFromCsvFile(@PathVariable String job) {
        return 0.0;
    }





}








