package com.nbu.Graduation_System.service.department;

import com.nbu.Graduation_System.dto.department.*;
import java.util.List;

public interface DepartmentService {
    DepartmentDto save(CreateDepartmentDto department);
    DepartmentDto findById(Long id);
    List<DepartmentDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    // Optional<DepartmentDto> findByType(DepartmentType type);
    // DepartmentDto updateContactInfo(Long departmentId, String email);
}
