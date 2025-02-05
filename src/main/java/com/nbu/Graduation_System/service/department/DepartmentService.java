package com.nbu.Graduation_System.service.department;

import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department save(Department department);
    Optional<Department> findById(Long id);
    List<Department> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<Department> findByType(DepartmentType type);
    Department updateContactInfo(Long departmentId, String email);
}
