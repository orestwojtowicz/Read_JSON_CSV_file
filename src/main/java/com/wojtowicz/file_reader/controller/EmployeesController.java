package com.wojtowicz.file_reader.controller;


import com.wojtowicz.file_reader.service.EmployeeService;
import com.wojtowicz.file_reader.shared.AppConstants;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@RestController
@RequestMapping(AppConstants.MAIN_ENDPOINT)
public class EmployeesController {


    /**
     * MAIN_ENDPOINT - /employees
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("v1/{job}/salaries/json")
    public String getSingleSalaryFromJsonFile(@PathVariable String job) throws NotFoundException {

        return "json file - sum salary for job "
                + job + " = " + employeeService.getSumOfEarningsFromJson(job);
    }


    /**
     * Endpoint for retrieving sum of salary for given job
     * http://localhost:8080/employees/v2/Janitor/salaries/csv
     * @param job might be Teacher, Priest, Janitor
     * */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("v2/{job}/salaries/csv")
    public String getSingleSalaryFromCsvFile(@PathVariable String job) throws NotFoundException {

        return "csv file - sum salary for job "
                + job + " = " + employeeService.getSumOfEarningsFromCsv(job);
    }

}








