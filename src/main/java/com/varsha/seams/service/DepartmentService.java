package com.varsha.seams.service;
import java.util.List;

import com.varsha.seams.entity.Department;
public interface DepartmentService {
	Department saveDepartment(Department department);

    List<Department> getAllDepartments();
}
