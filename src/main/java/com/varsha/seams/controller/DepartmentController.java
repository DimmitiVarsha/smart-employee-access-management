package com.varsha.seams.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.varsha.seams.entity.Department;
import com.varsha.seams.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	@Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}
