package com.wojtowicz.file_reader.files;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 29.01.20
 */

public class FileCSVTest {


    @BeforeEach
    public void setUp() {

    }


/*          id,name,surname,job,salary
            1,Mark,Green,Teacher,3540.2
            2,Oscar,Mustache,Janitor,13460.45
            3,Adalbert,Kidney,Priest,15240.00
            4,Christopher,Głuś,Teacher,2700.1
            5,Michael,Spear,Janitor,13460.45*/


    @DisplayName("CSV from file test")
    @ParameterizedTest(name = "{index} => id={0}, name={1}, surname={2}, job={3}, salary={4}")
    @CsvFileSource(resources = "/files/employees.csv", numLinesToSkip = 1)
    public void exmpl(int id, String name, String surname, String job, double salary) {


    List<String> csvFileContent = new ArrayList<>();
    csvFileContent.add(name);

    assertNotNull(csvFileContent);
    assertEquals(1,csvFileContent.size());

    }

}