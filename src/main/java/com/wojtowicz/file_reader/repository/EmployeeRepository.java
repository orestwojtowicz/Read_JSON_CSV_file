package com.wojtowicz.file_reader.repository;

import com.wojtowicz.file_reader.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByJob(String job);

}
