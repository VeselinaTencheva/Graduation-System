package com.nbu.Graduation_System.service.student;

import com.nbu.Graduation_System.dto.student.CreateStudentDto;
import com.nbu.Graduation_System.dto.student.StudentDto;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.repository.StudentRepository;
import com.nbu.Graduation_System.repository.DepartmentRepository;
import com.nbu.Graduation_System.repository.UserRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    @Override
    public StudentDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                studentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student with id=" + id + " not found!")),
                StudentDto.class);
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
        
        
        // Set the department
        Department department = departmentRepository.findById(studentDto.getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department not found with id: " + studentDto.getDepartmentId()));
        student.setDepartment(department);
        
        student = studentRepository.save(student);
        return mapperUtil.getModelMapper().map(student, StudentDto.class);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
}
