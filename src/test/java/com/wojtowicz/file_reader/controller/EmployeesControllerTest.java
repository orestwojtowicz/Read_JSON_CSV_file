package com.wojtowicz.file_reader.controller;


import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import com.wojtowicz.file_reader.repository.EmployeeJsonRepository;
import com.wojtowicz.file_reader.service.EmployeeService;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 29.01.20
 */

public class EmployeesControllerTest {

    private MockMvc mockMvc;


    private EmployeesController employeesController;


    @Mock
    private EmployeeService employeeService;

   @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeesController = new EmployeesController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();
    }

    // "v2/{job}/salaries/csv"
    @Test
    public void getSalaryForSingleJobCsv() throws Exception {
        EmployeeCSVEntity employeeCSVEntity = new EmployeeCSVEntity();
        employeeCSVEntity.setJob("Teacher");
        employeeCSVEntity.setSalary(100.00);

        given(employeeService.getSumOfEarningsFromCsv(employeeCSVEntity.getJob()))
                .willReturn(String.valueOf(employeeCSVEntity.getSalary()));

        MvcResult mvcResult = mockMvc.perform(
                get("/employees/v2/Teacher/salaries/csv"))
                .andExpect(status().isOk())
                .andReturn();

        String mvcResultResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(mvcResultResponse, "csv file - sum salary for job Teacher = 100.0");
        then(employeeService).should().getSumOfEarningsFromCsv(anyString());

    }
// -> at com.wojtowicz.file_reader.controller.EmployeesControllerTest.getSalaryForSingleJobCsv
    @Test
    public void getSalaryForSingleJobJson() throws Exception {
        EmployeeJsonEntity employeeJSONEntity = new EmployeeJsonEntity();

        employeeJSONEntity.setJob("Janitor");
        employeeJSONEntity.setSalary(150.00);

        given(employeeService.getSumOfEarningsFromJson(employeeJSONEntity.getJob()))
                .willReturn(String.valueOf(employeeJSONEntity.getSalary()));

        MvcResult mvcResult = mockMvc.perform(
                get("/employees/v1/Janitor/salaries/json"))
                .andExpect(status().isOk())
                .andReturn();

        String mvcResultResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(mvcResultResponse, "json file - sum salary for job Janitor = 150.0");
        then(employeeService).should().getSumOfEarningsFromJson(anyString());

    }


}
















