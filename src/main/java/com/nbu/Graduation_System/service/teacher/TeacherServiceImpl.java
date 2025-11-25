package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.dto.teacher.CreateTeacherDto;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.teacher.UpdateTeacherDto;
import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.enums.UserRoleType;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.repository.UserRepository;
import com.nbu.Graduation_System.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TeacherDto findById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher with id=" + id + " not found!"));

        return mapperUtil.getModelMapper().map(teacher, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> findAll() {
        return mapperUtil.mapList(teacherRepository.findAll(), TeacherDto.class);
    }

    @Override
    public List<TeacherDto> findAllByDepartmentId(Long departmentId) {
        return mapperUtil.mapList(
            teacherRepository.findByDepartmentId(departmentId).stream()
                .toList(), 
            TeacherDto.class
        );
    }

    @Override
    public TeacherDto save(CreateTeacherDto teacherDto) {
        if (userRepository.existsByEmail(teacherDto.getEmail())) {
            throw new RuntimeException("Email already exists: " + teacherDto.getEmail());
        }

        Teacher teacher = mapperUtil.getModelMapper().map(teacherDto, Teacher.class);

        String encodedPassword = passwordEncoder.encode(teacherDto.getPassword());
        teacher.setPassword(encodedPassword);
        teacher.setRole(UserRoleType.TEACHER);

        Department department = departmentRepository.findById(teacherDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + teacherDto.getDepartmentId()));
        teacher.setDepartment(department);

        teacher = teacherRepository.save(teacher);
        return mapperUtil.getModelMapper().map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto update(Long id, UpdateTeacherDto dto) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher with id=" + id + " not found!"));

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }
        if (dto.getRole() != null) {
            existing.setRole(dto.getRole());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getAcademicTitle() != null) {
            existing.setAcademicTitle(dto.getAcademicTitle());
        }
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));
            existing.setDepartment(department);
        }

        existing = teacherRepository.save(existing);
        return mapperUtil.getModelMapper().map(existing, TeacherDto.class);
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
