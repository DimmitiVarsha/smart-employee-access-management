package com.varsha.seams.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.varsha.seams.entity.Employee;
import com.varsha.seams.enums.EmployeeStatus;
import com.varsha.seams.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully.";
    }
    @GetMapping("/search/name/{name}")
    public List<Employee> searchEmployeeByName(@PathVariable String name){
    	return employeeService.searchByName(name);
    	
    }
    @GetMapping("/search/email/{email}")
    public Employee searchEmployeeByEmail(@PathVariable String email) {
        return employeeService.searchByEmail(email);
    }
    @GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }
    @GetMapping("/search")
    public List<Employee> searchEmployee(@RequestParam String name) {
        return employeeService.getEmployeesByName(name);
    }
    /*@GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }*/
    @GetMapping("/status/{status}")
    public List<Employee> getEmployeesByStatus(@PathVariable EmployeeStatus status) {
        return employeeService.getEmployeesByStatus(status);
    }
    @GetMapping("/department/{departmentId}/count")
    	public long countEmployees(@PathVariable Long departmentId) {
    		return employeeService.countEmployeesByDepartment(departmentId);
    	}
    }
    
