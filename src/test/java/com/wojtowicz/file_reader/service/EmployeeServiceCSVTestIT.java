package com.wojtowicz.file_reader.service;

import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.repository.EmployeeCSVRepository;


import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 29.01.20
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceCSVTestIT {



    @Autowired
    private EmployeeCSVRepository employeeCSVRepository;

    @Autowired
    private EmployeeService employeeService;



    @Test
    @DisplayName("Test for finding value from CSV file by given id, id = 1, Mark")
    public void testFindByIdCSV() {

        Optional<EmployeeCSVEntity> christopher = employeeCSVRepository.findById(1L);
        assertNotNull(christopher.get());
        assertEquals("Mark", christopher.get().getName());
        assertEquals("Green", christopher.get().getSurname());
        assertEquals("Teacher", christopher.get().getJob());
        assertEquals(3540.20, christopher.get().getSalary());
    }


    @Test
    @DisplayName("Test for finding all entities from CSV file, based on their job")
    public void testFindAllByJobCSV() {
        List<EmployeeCSVEntity> foundAllJobs = employeeCSVRepository.findAllByJob("Janitor");

        assertNotNull(foundAllJobs);
        assertEquals(2,foundAllJobs.size(), "We got two Janitors in database");
    }

    @Test
    @DisplayName("Test for summing Janitor salary values from CSV file")
    public void testSumSalaryOfGivenJobJanitorCSV() throws NotFoundException {

        // "13460.45" "13460.45" = 26920.90
        String sumOfSalaryForTeachers = employeeService.getSumOfEarningsFromJson("Janitor");
        assertNotNull(sumOfSalaryForTeachers);
        assertEquals("26920.90",sumOfSalaryForTeachers,"Sum of 13460.45 + 13460.45 = 26820.90");
    }

    @Test
    @DisplayName("Test for summing Priest salary values from CSV file")
    public void testSumSalaryOfGivenJobPriestCSV() throws NotFoundException {
        // "15240.00"
        String sumOfSalaryForTeachers = employeeService.getSumOfEarningsFromJson("Priest");
        assertNotNull(sumOfSalaryForTeachers);
        assertEquals("15240.00",sumOfSalaryForTeachers,"Sum of 15240.00 = 15240.00");
    }




}












