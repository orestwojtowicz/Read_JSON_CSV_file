package com.wojtowicz.file_reader.repository;

import com.wojtowicz.file_reader.domain.entity.EmployeeCSVEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Repository
public interface EmployeeCSVRepository extends JpaRepository<EmployeeCSVEntity, Long> {


    /**
     * Query for getting all jobs related to given Entity - CSV
     * @param job Teacher, Priest, Janitor
     * */
    List<EmployeeCSVEntity> findAllByJob(String job);



}
