package com.wojtowicz.file_reader.domain;


import com.opencsv.bean.CsvBindByPosition;
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
public class EmployeeCSV {


    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String surname;

    @CsvBindByPosition(position = 3)
    private String job;

    @CsvBindByPosition(position = 4)
    private String salary;


}
