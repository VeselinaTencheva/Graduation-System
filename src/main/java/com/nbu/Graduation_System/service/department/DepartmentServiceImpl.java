package com.nbu.Graduation_System.service.department;

import com.nbu.Graduation_System.dto.DepartmentDto;
import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.mapper.DepartmentMapper;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                               DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = departmentMapper.toEntity(departmentDto);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    @Override
    public Optional<DepartmentDto> findById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
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
    public Optional<DepartmentDto> findByType(DepartmentType type) {
        return departmentRepository.findByType(type)
                .map(departmentMapper::toDto);
    }

    @Override
    public DepartmentDto updateContactInfo(Long departmentId, String email) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        department.setContactEmail(email);
        
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }
}
