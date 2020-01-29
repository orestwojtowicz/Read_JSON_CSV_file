package com.wojtowicz.file_reader.service;

import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;
import com.wojtowicz.file_reader.repository.EmployeeJsonRepository;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


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


    public void readCSVFile(String csvFilePath) throws FileNotFoundException {
        if (csvFilePath == null) {
            throw new FileNotFoundException("File path for csv file can't be found");
        }
        readEmployeePojoFromCSVFileAndSaveItToDatabase(csvFilePath);
    }

    public void readJSONFile(String jsonFilePath) throws IOException {
        if (jsonFilePath == null) {
            throw new FileNotFoundException("File path for JSON file can't be found");
        }
        readEmployeePojoFromJSONFileAndSaveItToDatabase(jsonFilePath);
    }


    /**
     * Method for finding employeeJson entity based on jobName & calculate sum of earnings for given job
     * If we had more record use of EntityManager would be batter approach:
     * SUM(salary) FROM Employee WHERE job = :job.createNativeQuery()
     * double sum = getSingleResult()
     *
     * @param jobName Teacher, Priest, Janitor
     * @return value with double precision
     */

    public String getSumOfEarningsFromJson(String jobName) throws NotFoundException {

        List<EmployeeJsonEntity> listJobs = employeeJSONRepository.findAllByJob(jobName);

        if (listJobs.isEmpty()) {
            throw new NotFoundException("there is no record for given job " + jobName);
        }

        double sum =
                listJobs
                        .stream()
                        .mapToDouble(EmployeeJsonEntity::getSalary)
                        .sum();

        return String.format("%.2f", sum);
    }

    /**
     * Method for finding employeeCsv entity based on jobName & calculate sum of earnings for given job
     * If we had more record use of EntityManager would be batter approach:
     * EntityManager.createNativeQuery(String
     *
     * @param jobName Teacher, Priest, Janitor
     * @return value with double precision
     */
    public String getSumOfEarningsFromCsv(String jobName) throws NotFoundException {

        List<EmployeeCSVEntity> listJobs = employeeCSVRepository.findAllByJob(jobName);

        if (listJobs.isEmpty()) {
            throw new NotFoundException("there is no record for given job " + jobName);
        }

        double sum =
                listJobs
                        .stream()
                        .mapToDouble(EmployeeCSVEntity::getSalary)
                        .sum();

        return String.format("%.2f", sum);
    }
}











