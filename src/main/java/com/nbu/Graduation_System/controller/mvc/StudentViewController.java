package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.student.CreateStudentDto;
import com.nbu.Graduation_System.service.student.StudentService;
import com.nbu.Graduation_System.service.department.DepartmentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.department.DepartmentViewModel;
import com.nbu.Graduation_System.viewmodel.student.CreateStudentViewModel;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;

import jakarta.validation.Valid;

import java.util.List;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentViewController {
    private final StudentService studentService;
    private final DepartmentService departmentService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String listStudents(Model model) {
        List<StudentViewModel> students = mapperUtil
                .mapList(this.studentService.findAll(), StudentViewModel.class);
        model.addAttribute("students", students);
        return "/students/list";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable("id") Long id, Model model) {
        StudentViewModel student = mapperUtil.getModelMapper().map(
                studentService.findById(id), StudentViewModel.class);
        model.addAttribute("student", student);
        return "students/view";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("student", new CreateStudentViewModel());
        model.addAttribute("departments", mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
        return "students/form";
    }

    @PostMapping
    public String createStudent(
            @Valid @ModelAttribute("student") CreateStudentViewModel student,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
            return "students/form";
        }

        try {
            CreateStudentDto dto = mapperUtil.getModelMapper().map(student, CreateStudentDto.class);
            studentService.save(dto);
            redirectAttributes.addFlashAttribute("success", "Student created successfully!");
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("departments", mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
            model.addAttribute("error", "Failed to create student: " + e.getMessage());
            return "students/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.studentService.deleteById(id);
        return "redirect:/students";
    }
}

