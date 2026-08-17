package com.varsha.seams.serviceImpli;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varsha.seams.entity.Employee;
import com.varsha.seams.enums.EmployeeStatus;
import com.varsha.seams.repository.EmployeeRepository;
import com.varsha.seams.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        employee.setEmployeeId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public List<Employee> searchByName(String name){
    	return employeeRepository.findByEmployeeNameContainingIgnoreCase(name);
    }
    @Override
    public Employee searchByEmail(String email) {
    	return employeeRepository.findByEmail(email).orElse(null);
    }
    @Override
    public List<Employee> getEmployeesByDepartment(Long departmentId){
    		return employeeRepository.findByDepartmentDepartmentId(departmentId);
    }
    @Override
    public List<Employee> getEmployeesByName(String employeeName) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(employeeName);
     }
    @Override
    public List<Employee> getEmployeesByStatus(EmployeeStatus status){
    	return employeeRepository.findByStatus(status);
    }
    @Override
    public long countEmployeesByDepartment(Long departmentId) {
    	return employeeRepository.countByDepartmentDepartmentId(departmentId);
    	
    }
    
    
    
}
