package com.varsha.seams.service;
import java.util.List;

import com.varsha.seams.entity.Employee;
import com.varsha.seams.enums.EmployeeStatus;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
    List<Employee> searchByName(String name);
    List<Employee> getEmployeesByName(String employeeName);
    Employee searchByEmail(String emil);
    List<Employee> getEmployeesByDepartment(Long departmentId);
    List<Employee> getEmployeesByStatus(EmployeeStatus status);
    long countEmployeesByDepartment(Long departmentId);
}
