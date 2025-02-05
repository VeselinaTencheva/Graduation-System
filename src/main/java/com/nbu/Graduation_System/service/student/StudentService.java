package com.nbu.Graduation_System.service.student;

import com.nbu.Graduation_System.dto.StudentDto;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDto save(StudentDto student);
    Optional<StudentDto> findById(Long id);
    List<StudentDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<StudentDto> findByFacultyNumber(String facultyNumber);
}
