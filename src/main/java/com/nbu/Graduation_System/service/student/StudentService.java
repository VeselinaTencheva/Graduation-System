package com.nbu.Graduation_System.service.student;

import java.util.List;

import com.nbu.Graduation_System.dto.student.CreateStudentDto;
import com.nbu.Graduation_System.dto.student.StudentDto;
import com.nbu.Graduation_System.dto.student.UpdateStudentDto;

public interface StudentService {
    StudentDto save(CreateStudentDto student);
    StudentDto findById(Long id);
    List<StudentDto> findAll();
    List<StudentDto> findAllEligibleForThesisApplication();
    List<StudentDto> findAllEligibleForThesisApplicationByDepartment(Long departmentId);
    StudentDto update(Long id, UpdateStudentDto dto);
    void deleteById(Long id);
    boolean existsById(Long id);
    // Optional<StudentDto> findByFacultyNumber(String facultyNumber);
}
