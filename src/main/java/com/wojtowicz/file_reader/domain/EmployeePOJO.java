package com.wojtowicz.file_reader.domain;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeePOJO {




    @JsonProperty("employees")
    private List<Employee> employees;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;
    @JsonProperty("job")
    private String job;

    @JsonProperty("salaray")
    private double salary;



}
