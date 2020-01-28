package com.wojtowicz.file_reader.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.wojtowicz.file_reader.domain.Employee;
import com.wojtowicz.file_reader.domain.EmployeeCSV;
import com.wojtowicz.file_reader.domain.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.EmployeePOJO;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepo;
import com.wojtowicz.file_reader.repository.EmployeeRepository;
import org.hibernate.bytecode.enhance.internal.javassist.PersistentAttributesHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Component
public class BootstrapData implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeCSVRepo repo;

    public BootstrapData(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }





    private static final String PATH = "/home/damiass/Desktop/file_reader/src/main/resources/files/employees.csv";

    @Override
    public void run(String... args) throws Exception {

        readEmployeePojoAndSaveItToDatabase();

        Reader reader = Files.newBufferedReader(Paths.get(PATH));

        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(EmployeeCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(0)
                .build();

        List<EmployeeCSV> empls = csvToBean.parse();

        ModelMapper mapper = new ModelMapper();



        for (EmployeeCSV em : empls) {
            if (em.getId().equals("id")) {
                continue;
            }
            EmployeeCSVEntity employeeCSVEntity = new EmployeeCSVEntity();
            employeeCSVEntity = mapper.map(em,EmployeeCSVEntity.class);
            repo.save(employeeCSVEntity);
        }








       // empls.forEach(x -> System.out.println(x.getId()));


        reader.close();




        }



    private void readEmployeePojoAndSaveItToDatabase() throws Exception {
        EmployeePOJO employees = (objectMapper
                .readValue(new File(
                        "/home/damiass/Desktop/file_reader/src/main/resources/static/employees.json"), EmployeePOJO.class));

        for (Employee pojo : employees.getEmployees()) {
             employeeRepository.save(pojo);
        }
    }


}
