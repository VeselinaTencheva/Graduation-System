package com.nbu.Graduation_System.service.student;

import com.nbu.Graduation_System.dto.student.CreateStudentDto;
import com.nbu.Graduation_System.dto.student.StudentDto;
import com.nbu.Graduation_System.dto.student.UpdateStudentDto;
import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.enums.UserRoleType;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import com.nbu.Graduation_System.repository.StudentRepository;
import com.nbu.Graduation_System.repository.UserRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final PasswordEncoder encoder;

    @Override
    public StudentDto findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id=" + id + " not found!"));

        return mapperUtil.getModelMapper().map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> findAll() {
        return mapperUtil.mapList(studentRepository.findAll(), StudentDto.class);
    }

    @Override
    public List<StudentDto> findAllEligibleForThesisApplication() {
        return mapperUtil.mapList(studentRepository.findAllEligibleForThesisApplication(), StudentDto.class);
    }

    @Override
    public List<StudentDto> findAllEligibleForThesisApplicationByDepartment(Long departmentId) {
        return mapperUtil.mapList(
            studentRepository.findAllEligibleForThesisApplication().stream()
                .filter(student -> student.getDepartment().getId().equals(departmentId))
                .toList(), 
            StudentDto.class
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id=" + id + " not found!");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto save(CreateStudentDto studentDto) {
        if (userRepository.existsByEmail(studentDto.getEmail())) {
            throw new RuntimeException("Email already exists: " + studentDto.getEmail());
        }

        Student student = mapperUtil.getModelMapper().map(studentDto, Student.class);
        final String encodedPassword = encoder.encode(studentDto.getPassword());
        student.setPassword(encodedPassword);
        student.setRole(UserRoleType.STUDENT);

        Department department = departmentRepository.findById(studentDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + studentDto.getDepartmentId()));
        student.setDepartment(department);
        
        student = studentRepository.save(student);
        return mapperUtil.getModelMapper().map(student, StudentDto.class);
    }

    @Override
    public StudentDto update(Long id, UpdateStudentDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id=" + id + " not found!"));

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(encoder.encode(dto.getPassword()));
        }
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));
            existing.setDepartment(department);
        }

        existing = studentRepository.save(existing);
        return mapperUtil.getModelMapper().map(existing, StudentDto.class);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
}
