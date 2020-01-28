package com.wojtowicz.file_reader.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.domain.pojos.EmployeeCSVPojo;
import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.pojos.EmployeeJsonPojo;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;
import com.wojtowicz.file_reader.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeRepository employeeRepository;
    private final EmployeeCSVRepository employeeCSVRepository;



    public BootstrapData(EmployeeRepository employeeRepository, EmployeeCSVRepository employeeCSVRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCSVRepository = employeeCSVRepository;
    }


    private static final String PATH = "/home/damiass/Desktop/file_reader/src/main/resources/files/employees.csv";

    @Override
    public void run(String... args) throws Exception {

        readEmployeePojoAndSaveItToDatabase();
        readEmployeeFromCSVFileAndSaveItToDatabase();


    }


    private void readEmployeeFromCSVFileAndSaveItToDatabase() {

        ModelMapper mapper = new ModelMapper();

        try (Reader reader = Files.newBufferedReader(Paths.get(PATH))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(EmployeeCSVPojo.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(0)
                    .build();

            List<EmployeeCSVPojo> empls = csvToBean.parse();

            for (EmployeeCSVPojo em : empls) {
                if (em.getId().equals("id")) {
                    continue;
                }
                EmployeeCSVEntity employeeCSVEntity = new EmployeeCSVEntity();
                employeeCSVEntity = mapper.map(em, EmployeeCSVEntity.class);
                employeeCSVRepository.save(employeeCSVEntity);
                log.info("CSV file has been saved successfully to database");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void readEmployeePojoAndSaveItToDatabase() throws Exception {
        EmployeeJsonPojo employees = (objectMapper
                .readValue(new File(
                        "/home/damiass/Desktop/file_reader/src/main/resources/static/employees.json"), EmployeeJsonPojo.class));

        for (EmployeeJsonEntity pojo : employees.getEmployees()) {
             employeeRepository.save(pojo);
        }
        log.info("JSON file has been saved successfully to database");
    }


}
