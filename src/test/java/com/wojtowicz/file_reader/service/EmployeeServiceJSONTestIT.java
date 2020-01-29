package com.wojtowicz.file_reader.service;

import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.repository.EmployeeJsonRepository;

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
public class EmployeeServiceJSONTestIT {

    @Autowired
    private EmployeeJsonRepository employeeJSONRepository;


    @Autowired
    private EmployeeService employeeService;


    /**
     * "id": 4,
     * "name": "Christopher",
     * "surname": "Głuś",
     * "job": "Teacher",
     * "salary": "2700.10"
     * <p>
     * <p>
     * "id": 1,
     * "name": "Mark",
     * "surname": "Green",
     * "job": "Teacher",
     * "salary": "3540.20"
     */


    @Test
    @DisplayName("Test for finding value from JSON file by given id = 4, Christopher")
    public void testFindByIdJSON() {

        Optional<EmployeeJsonEntity> christopher = employeeJSONRepository.findById(4L);
        assertNotNull(christopher.get());
        assertEquals("Christopher", christopher.get().getName());
        assertEquals("Głuś", christopher.get().getSurname());
        assertEquals("Teacher", christopher.get().getJob());
        assertEquals(2700.10, christopher.get().getSalary());

    }


    @Test
    @DisplayName("Test for finding all entities from JSON file, based on their job")
    public void testFindAllByJobJSON() {
        List<EmployeeJsonEntity> foundAllJobs = employeeJSONRepository.findAllByJob("Teacher");

        assertNotNull(foundAllJobs);
        assertEquals(2, foundAllJobs.size(), "We got two Teachers in database");
    }

    @Test
    @DisplayName("Test for summing Teacher salary values from JSON file")
    public void testSumSalaryOfGivenJobTeacherJSON() throws NotFoundException {

        // "2700.10" + "3540.20"
        String sumOfSalaryForTeachers = employeeService.getSumOfEarningsFromJson("Teacher");
        assertNotNull(sumOfSalaryForTeachers);
        assertEquals("6240.30", sumOfSalaryForTeachers, "Sum of 2700.10 + 3540.20 = 6240.30");

    }

    /*
            "id": 3,
            "name": "Adalbert",
            "surname": "Kidney",
            "job": "Priest",
            "salary": "15240.00"
    */

    @Test
    @DisplayName("Test for summing Priest salary values")
    public void testSumSalaryOfGivenJobPriestJSON() throws NotFoundException {

        String sumOfSalaryForTeachers = employeeService.getSumOfEarningsFromJson("Priest");
        assertNotNull(sumOfSalaryForTeachers);
        assertEquals("15240.00", sumOfSalaryForTeachers, "Sum of 15240.00 = 15240.00");
    }

}




















