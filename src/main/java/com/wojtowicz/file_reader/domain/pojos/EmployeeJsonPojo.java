package com.wojtowicz.file_reader.domain.pojos;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
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
public class EmployeeJsonPojo {




    @JsonProperty("employees")
    private List<EmployeeJsonEntity> employees;

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
