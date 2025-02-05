package com.nbu.Graduation_System.service.student;

import com.nbu.Graduation_System.dto.StudentDto;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.mapper.StudentMapper;
import com.nbu.Graduation_System.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public Optional<StudentDto> findById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Optional<StudentDto> findByFacultyNumber(String facultyNumber) {
        return studentRepository.findByFacultyNumber(facultyNumber)
                .map(studentMapper::toDto);
    }
}
