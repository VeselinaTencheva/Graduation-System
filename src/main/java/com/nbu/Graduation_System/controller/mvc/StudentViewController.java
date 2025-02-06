package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.service.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;

import java.util.List;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentViewController {
    private final StudentService studentService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String listStudents(Model model) {
        List<StudentViewModel> students = mapperUtil
                .mapList(this.studentService.findAll(), StudentViewModel.class);
        model.addAttribute("students", students);
        return "/students/list";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        StudentViewModel student = mapperUtil.getModelMapper().map(
                studentService.findById(id), StudentViewModel.class);
        model.addAttribute("student", student);
        return "students/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.studentService.deleteById(id);
        return "redirect:/students";
    }
}

