package com.wojtowicz.file_reader.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.domain.pojos.EmployeeCSVPojo;
import com.wojtowicz.file_reader.domain.pojos.EmployeeJsonPojo;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;
import com.wojtowicz.file_reader.repository.EmployeeJsonRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 29.01.20
 */

@Slf4j
public abstract class ReadJsonAndCsvFiles {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper mapper = new ModelMapper();
    private final EmployeeJsonRepository employeeJSONRepository;
    private final EmployeeCSVRepository employeeCSVRepository;

    public ReadJsonAndCsvFiles(EmployeeCSVRepository employeeCSVRepository,
                               EmployeeJsonRepository employeeJSONRepository) {

        this.employeeCSVRepository = employeeCSVRepository;
        this.employeeJSONRepository = employeeJSONRepository;
    }





    public void readEmployeePojoFromCSVFileAndSaveItToDatabase(String csvFilePath) {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
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

    public void readEmployeePojoFromJSONFileAndSaveItToDatabase(String jsonFilePath) throws IOException {
        EmployeeJsonPojo employees = (objectMapper
                .readValue(new File(
                        jsonFilePath), EmployeeJsonPojo.class));

        for (EmployeeJsonEntity pojo : employees.getEmployees()) {
            employeeJSONRepository.save(pojo);
        }
        log.info("JSON file has been saved successfully to database");
    }


}
