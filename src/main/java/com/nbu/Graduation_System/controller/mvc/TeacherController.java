package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.TeacherDto;
import com.nbu.Graduation_System.service.teacher.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teachers/list";
    }

    @GetMapping("/{id}")
    public String viewTeacher(@PathVariable Long id, Model model) {
        return teacherService.findById(id)
                .map(teacher -> {
                    model.addAttribute("teacher", teacher);
                    return "teachers/view";
                })
                .orElse("redirect:/teachers");
    }

    @GetMapping("/new")
    public String newTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDto());
        return "teachers/form";
    }

    @PostMapping
    public String createTeacher(@ModelAttribute TeacherDto teacherDto, RedirectAttributes redirectAttributes) {
        TeacherDto savedTeacher = teacherService.save(teacherDto);
        redirectAttributes.addFlashAttribute("message", "Teacher created successfully");
        return "redirect:/teachers/" + savedTeacher.getId();
    }

    @GetMapping("/{id}/edit")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        return teacherService.findById(id)
                .map(teacher -> {
                    model.addAttribute("teacher", teacher);
                    return "teachers/form";
                })
                .orElse("redirect:/teachers");
    }

    @PostMapping("/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute TeacherDto teacherDto, 
                              RedirectAttributes redirectAttributes) {
        if (!teacherService.existsById(id)) {
            return "redirect:/teachers";
        }
        teacherDto.setId(id);
        teacherService.save(teacherDto);
        redirectAttributes.addFlashAttribute("message", "Teacher updated successfully");
        return "redirect:/teachers/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (teacherService.existsById(id)) {
            teacherService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Teacher deleted successfully");
        }
        return "redirect:/teachers";
    }
}
