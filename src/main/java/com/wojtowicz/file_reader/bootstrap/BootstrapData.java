package com.wojtowicz.file_reader.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.domain.pojos.EmployeeCSVPojo;
import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.pojos.EmployeeJsonPojo;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;
import com.wojtowicz.file_reader.repository.EmployeeJsonRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.wojtowicz.file_reader.shared.AppConstants.FILE_CSV_PATH;
import static com.wojtowicz.file_reader.shared.AppConstants.FILE_JSON_PATH;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeJsonRepository employeeRepository;
    private final EmployeeCSVRepository employeeCSVRepository;


    public BootstrapData(EmployeeJsonRepository employeeRepository, EmployeeCSVRepository employeeCSVRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCSVRepository = employeeCSVRepository;
    }

    /**
     * Method for populating data to database from CSV and JSON file
     */

    @Override
    public void run(String... args) throws Exception {

        readEmployeePojoFromJSONFileAndSaveItToDatabase();
        readEmployeePojoFromCSVFileAndSaveItToDatabase();
    }


    private void readEmployeePojoFromCSVFileAndSaveItToDatabase() {

        ModelMapper mapper = new ModelMapper();

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_CSV_PATH))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(EmployeeCSVPojo.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(0)
                    .build();

            List<EmployeeCSVPojo> emplCsvList = csvToBean.parse();

            for (EmployeeCSVPojo singleEmployee : emplCsvList) {

                EmployeeCSVEntity employeeCSVEntity;
                employeeCSVEntity = mapper.map(singleEmployee, EmployeeCSVEntity.class);
                employeeCSVRepository.save(employeeCSVEntity);
            }
            log.info("CSV file has been saved successfully to database");

        } catch (IOException e) {
            System.out.println("Error while saving CSV file to database ");
            e.printStackTrace();
        }
    }

    private void readEmployeePojoFromJSONFileAndSaveItToDatabase() throws IOException {
        EmployeeJsonPojo employees = (objectMapper
                .readValue(new File(
                        FILE_JSON_PATH), EmployeeJsonPojo.class));

        for (EmployeeJsonEntity pojo : employees.getEmployees()) {
            employeeRepository.save(pojo);
        }
        log.info("JSON file has been saved successfully to database");
    }
}
