package com.nbu.Graduation_System.controller.api;

import com.nbu.Graduation_System.dto.student.CreateStudentDto;
import com.nbu.Graduation_System.dto.student.StudentDto;
import com.nbu.Graduation_System.dto.student.UpdateStudentDto;
import com.nbu.Graduation_System.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentApiController {
    
    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    public StudentDto createStudent(@Valid @RequestBody CreateStudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @PutMapping("/{id}")
    public StudentDto updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStudentDto studentDto
    ) {
        return studentService.update(id, studentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found", ex);
        }
    }

    @GetMapping("/eligible")
    public List<StudentDto> getEligibleForThesis() {
        return studentService.findAllEligibleForThesisApplication();
    }

    @GetMapping("/eligible/department/{departmentId}")
    public List<StudentDto> getEligibleForThesisByDepartment(@PathVariable Long departmentId) {
        return studentService.findAllEligibleForThesisApplicationByDepartment(departmentId);
    }
}
