package com.nbu.Graduation_System.service.student;

import com.nbu.Graduation_System.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);
    Optional<Student> findById(Long id);
    List<Student> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<Student> findByFacultyNumber(String facultyNumber);
}
