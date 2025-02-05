package com.nbu.Graduation_System.service.department;

import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public Optional<Department> findByType(DepartmentType type) {
        return departmentRepository.findByType(type);
    }

    @Override
    public Department updateContactInfo(Long departmentId, String email) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        department.setContactEmail(email);
        
        return departmentRepository.save(department);
    }
}
