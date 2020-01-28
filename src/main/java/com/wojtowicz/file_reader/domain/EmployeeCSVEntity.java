package com.wojtowicz.file_reader.domain;


import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class EmployeeCSVEntity {



    @Id
    @GeneratedValue
    private Long id;

    private String name;


    private String surname;


    private String job;


    private String salary;


}
