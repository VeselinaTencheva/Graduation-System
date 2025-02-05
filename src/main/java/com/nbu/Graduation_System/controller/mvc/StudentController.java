package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.StudentDto;
import com.nbu.Graduation_System.service.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        return studentService.findById(id)
                .map(student -> {
                    model.addAttribute("student", student);
                    return "students/view";
                })
                .orElse("redirect:/students");
    }

    @GetMapping("/faculty-number/{facultyNumber}")
    public String viewStudentByFacultyNumber(@PathVariable String facultyNumber, Model model) {
        return studentService.findByFacultyNumber(facultyNumber)
                .map(student -> {
                    model.addAttribute("student", student);
                    return "students/view";
                })
                .orElse("redirect:/students");
    }

    @GetMapping("/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new StudentDto());
        return "students/form";
    }

    @PostMapping
    public String createStudent(@ModelAttribute StudentDto studentDto, RedirectAttributes redirectAttributes) {
        StudentDto savedStudent = studentService.save(studentDto);
        redirectAttributes.addFlashAttribute("message", "Student created successfully");
        return "redirect:/students/" + savedStudent.getId();
    }

    @GetMapping("/{id}/edit")
    public String editStudentForm(@PathVariable Long id, Model model) {
        return studentService.findById(id)
                .map(student -> {
                    model.addAttribute("student", student);
                    return "students/form";
                })
                .orElse("redirect:/students");
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute StudentDto studentDto, 
                              RedirectAttributes redirectAttributes) {
        if (!studentService.existsById(id)) {
            return "redirect:/students";
        }
        studentDto.setId(id);
        studentService.save(studentDto);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully");
        return "redirect:/students/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (studentService.existsById(id)) {
            studentService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        }
        return "redirect:/students";
    }
}
