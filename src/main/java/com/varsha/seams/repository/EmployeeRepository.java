package com.varsha.seams.repository;
import java.util.*;
import com.varsha.seams.entity.Employee;
import com.varsha.seams.enums.EmployeeStatus;

import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findByEmployeeNameContainingIgnoreCase(String employeeName);
	Optional<Employee> findByEmail(String email);
	List<Employee> findByDepartmentDepartmentId(Long departmentId);
	List<Employee> findByStatus(EmployeeStatus status);
	long countByDepartmentDepartmentId(Long departmentId);
}
