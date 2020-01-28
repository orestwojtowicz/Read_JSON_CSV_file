package com.wojtowicz.file_reader.repository;

import com.wojtowicz.file_reader.domain.EmployeeCSVEntity;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@Repository
public interface EmployeeCSVRepo extends JpaRepository<EmployeeCSVEntity, Long> {
}
