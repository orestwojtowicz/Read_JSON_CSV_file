package com.wojtowicz.file_reader.service;

import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;
import com.wojtowicz.file_reader.repository.EmployeeJsonRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Service
public class EmployeeService extends ReadJsonAndCsvFiles {

    private final EmployeeJsonRepository employeeJSONRepository;
    private final EmployeeCSVRepository employeeCSVRepository;


    public EmployeeService(EmployeeJsonRepository employeeJSONRepository,
                           EmployeeCSVRepository employeeCSVRepository) {
        super(employeeCSVRepository, employeeJSONRepository);

        this.employeeJSONRepository = employeeJSONRepository;
        this.employeeCSVRepository = employeeCSVRepository;
    }



    public void readCSVFile(String csvFilePath) {
        readEmployeePojoFromCSVFileAndSaveItToDatabase(csvFilePath);
    }

    public void readJSONFile(String jsonFilePath) throws IOException {
        readEmployeePojoFromJSONFileAndSaveItToDatabase(jsonFilePath);
    }


    /**
     * Method for finding employeeJson entity based on jobName & calculate sum of earnings for given job
     * If we had more record use of EntityManager would be batter approach:
     * SUM(salary) FROM Employee WHERE job = :job.createNativeQuery()
     * double sum = getSingleResult()
     * @param jobName Teacher, Priest, Janitor
     * @return value with double precision
     * */

    public String getSumOfEarningsFromJson(String jobName) {

      double sum =  employeeJSONRepository.findAllByJob(jobName)
              .stream()
              .mapToDouble(EmployeeJsonEntity::getSalary)
              .sum();

        return String.format("%.2f",sum);
    }

    /**
     * Method for finding employeeCsv entity based on jobName & calculate sum of earnings for given job
     * If we had more record use of EntityManager would be batter approach:
     * EntityManager.createNativeQuery(String
     * @param jobName Teacher, Priest, Janitor
     * @return value with double precision
     * */
    public String getSumOfEarningsFromCsv(String jobName) {

    double sum =  employeeCSVRepository.findAllByJob(jobName)
            .stream()
            .mapToDouble(EmployeeCSVEntity::getSalary)
            .sum();

        return String.format("%.2f",sum);
    }
}











