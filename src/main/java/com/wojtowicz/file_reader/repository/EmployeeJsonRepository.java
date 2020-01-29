package com.wojtowicz.file_reader.repository;

import com.wojtowicz.file_reader.domain.entity.EmployeeJsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Repository
@Transactional
public interface EmployeeJsonRepository extends JpaRepository<EmployeeJsonEntity, Long> {

    /**
     * Query for getting all jobs related to given Entity - JSON
     * @param job Teacher, Priest, Janitor
     * */
    List<EmployeeJsonEntity> findAllByJob(String job);

    String findByJob(String jobName);


}
