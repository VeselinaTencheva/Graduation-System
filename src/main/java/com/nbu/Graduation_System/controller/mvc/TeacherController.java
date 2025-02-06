package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.service.teacher.TeacherService;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    
    private final TeacherService teacherService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String listTeachers(Model model) {
        List<TeacherDto> teachers = mapperUtil
                .mapList(this.teacherService.findAll(), TeacherDto.class);
        model.addAttribute("teachers", teachers);
        return "/teachers/list";
    }

    @GetMapping("/{id}")
    public String viewTeacher(@PathVariable Long id, Model model) {
        TeacherDto teacher = mapperUtil.getModelMapper().map(
                teacherService.findById(id), TeacherDto.class);
        model.addAttribute("teacher", teacher);
        return "/teachers/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}
