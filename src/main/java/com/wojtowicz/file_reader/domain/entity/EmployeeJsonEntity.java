package com.wojtowicz.file_reader.domain.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;



/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString
public class EmployeeJsonEntity {

    /**
     * Entity class representing database model for JSON file
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String job;
    private double salary;
}
