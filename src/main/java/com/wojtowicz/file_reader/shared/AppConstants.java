package com.wojtowicz.file_reader.shared;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

public class AppConstants {


    public static final String MAIN_ENDPOINT = "/employees";

    public static final String GET_SINGLE_SALARY_JSON_ENDPOINT = "v1/{job}/salaries/json";

    public static final String GET_SINGLE_SALARY_CSV_ENDPOINT = "v2/{job}/salaries/csv";

    // PATHS VARIABLES FOR FILES
    public static final String FILE_CSV_PATH = "src/main/resources/files/employees.csv";
    public static final String FILE_JSON_PATH = "src/main/resources/files/employees.json";


}
