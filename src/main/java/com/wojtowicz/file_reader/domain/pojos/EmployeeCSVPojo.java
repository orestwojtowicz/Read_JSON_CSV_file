package com.wojtowicz.file_reader.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeCSVPojo {

    /**
     * Plain Old Java Object - representing data schema for CSV file
     */


    private String id;
    private String name;
    private String surname;
    private String job;
    private String salary;

}
