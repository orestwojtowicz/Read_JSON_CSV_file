package com.wojtowicz.file_reader.controller;


import com.wojtowicz.file_reader.service.EmployeeService;
import com.wojtowicz.file_reader.shared.AppConstants;
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
@RequestMapping(AppConstants.MAIN_ENDPOINT)
public class EmployeesController {


    /**
     * Swagger available at: http://localhost:8080/swagger-ui.html#/
     * */

    private final EmployeeService employeeService;



    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * Endpoint for retrieving sum of salary for given job
     * "http://localhost:8080/employeesv1/v1/{job}/salaries/json"
     * @param job might be Teacher, Priest, Janitor
     * */
    @GetMapping(AppConstants.GET_SINGLE_SALARY_JSON_ENDPOINT)
    public String getSingleSalaryFromJsonFile(@PathVariable String job) {

        return "json file - sum salary for job "
                + job + " = " + employeeService.getSumOfEarningsFromJson(job);
    }


    /**
     * Endpoint for retrieving sum of salary for given job
     * http://localhost:8080/employees/v2/Janitor/salaries/csv
     * @param job might be Teacher, Priest, Janitor
     * */
    @GetMapping(AppConstants.GET_SINGLE_SALARY_CSV_ENDPOINT)
    public String getSingleSalaryFromCsvFile(@PathVariable String job) {

        return "csv file - sum salary for job "
                + job + " = " + employeeService.getSumOfEarningsFromCsv(job);
    }

}








