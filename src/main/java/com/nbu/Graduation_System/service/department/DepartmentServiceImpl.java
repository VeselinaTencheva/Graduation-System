package com.nbu.Graduation_System.service.department;

import com.nbu.Graduation_System.dto.department.*;
import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    private final MapperUtil mapperUtil;

    @Override
    public DepartmentDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                departmentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Department with id=" + id + " not found!")),
                DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return mapperUtil.mapList(departmentRepository.findAll(), DepartmentDto.class);
    }

    @Override
    public DepartmentDto save(CreateDepartmentDto departmentDto) {
        Department department = mapperUtil.getModelMapper().map(departmentDto, Department.class);
        department = departmentRepository.save(department);
        return mapperUtil.getModelMapper().map(department, DepartmentDto.class);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }

    // @Override
    // public Optional<DepartmentDto> findByType(DepartmentType type) {
    //     return departmentRepository.findByType(type)
    //             .map(department -> mapperUtil.getModelMapper().map(department, DepartmentDto.class));
    // }

    // @Override
    // public DepartmentDto updateContactInfo(Long departmentId, String email) {
    //     Department department = departmentRepository.findById(departmentId)
    //         .orElseThrow(() -> new RuntimeException("Department not found"));
    //     department.setContactEmail(email);
        
    //     department = departmentRepository.save(department);
    //     return mapperUtil.getModelMapper().map(department, DepartmentDto.class);
    // }
}
