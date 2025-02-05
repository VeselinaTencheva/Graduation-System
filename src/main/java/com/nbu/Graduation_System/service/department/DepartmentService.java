package com.nbu.Graduation_System.service.department;

import com.nbu.Graduation_System.dto.DepartmentDto;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto department);
    Optional<DepartmentDto> findById(Long id);
    List<DepartmentDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<DepartmentDto> findByType(DepartmentType type);
    DepartmentDto updateContactInfo(Long departmentId, String email);
}
