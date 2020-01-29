package com.wojtowicz.file_reader.bootstrap;


import com.wojtowicz.file_reader.service.EmployeeService;
import com.wojtowicz.file_reader.shared.AppConstants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    private final EmployeeService employeeService;


    public BootstrapData(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Method for populating data to database from CSV and JSON file
     */

    @Override
    public void run(String... args) throws Exception {


        employeeService.readCSVFile(AppConstants.FILE_CSV_PATH);

        employeeService.readJSONFile(AppConstants.FILE_JSON_PATH);


    }


}
