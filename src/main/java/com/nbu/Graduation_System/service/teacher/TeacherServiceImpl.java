package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.dto.teacher.CreateTeacherDto;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import com.nbu.Graduation_System.repository.UserRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    @Override
    public TeacherDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                teacherRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Teacher with id=" + id + " not found!")),
                TeacherDto.class);
    }

    @Override
    public List<TeacherDto> findAll() {
        return mapperUtil.mapList(teacherRepository.findAll(), TeacherDto.class);
    }

    @Override
    public TeacherDto save(CreateTeacherDto teacherDto) {
        if (userRepository.existsByEmail(teacherDto.getEmail())) {
            throw new RuntimeException("Email already exists: " + teacherDto.getEmail());
        }

        Teacher teacher = mapperUtil.getModelMapper().map(teacherDto, Teacher.class);
        
        
        
        // Set the department
        Department department = departmentRepository.findById(teacherDto.getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department not found with id: " + teacherDto.getDepartmentId()));
        teacher.setDepartment(department);
        
        teacher = teacherRepository.save(teacher);
        return mapperUtil.getModelMapper().map(teacher, TeacherDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher with id=" + id + " not found!");
        }
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }
}
