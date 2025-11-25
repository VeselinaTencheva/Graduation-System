package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.teacher.CreateTeacherDto;
import com.nbu.Graduation_System.dto.teacher.UpdateTeacherDto;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.service.teacher.TeacherService;
import com.nbu.Graduation_System.service.department.DepartmentService;
import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
import com.nbu.Graduation_System.service.thesis.ThesisDefenseService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.department.DepartmentViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.CreateTeacherViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.UpdateTeacherViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_defense.ThesisDefenseViewModel;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherViewController {
    
    private final TeacherService teacherService;
    private final ThesisApplicationService thesisApplicationService;
    private final ThesisDefenseService thesisDefenseService;
    private final DepartmentService departmentService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String listTeachers(Model model) {
        List<TeacherViewModel> teachers = mapperUtil
                .mapList(this.teacherService.findAll(), TeacherViewModel.class);
        model.addAttribute("teachers", teachers);
        return "/teachers/list";
    }

    @GetMapping("/{id}")
    public String viewTeacher(@PathVariable("id") Long id, Model model) {
        TeacherViewModel teacher = mapperUtil.getModelMapper().map(
                teacherService.findById(id), TeacherViewModel.class);
        model.addAttribute("teacher", teacher);
        return "/teachers/view";
    }

    @GetMapping("/by-department/{id}")
    public String listTeachersByDepartmentId(@PathVariable("id") Long id, Model model) {
        List<TeacherViewModel> teachers = mapperUtil
                .mapList(this.teacherService.findAllByDepartmentId(id), TeacherViewModel.class);
        model.addAttribute("teachers", teachers);
        return "/teachers/list";
    }

    @GetMapping("/{id}/applications")
    public String listThesisApplicationsByTeacherId(@PathVariable("id") Long id, Model model) {
        List<ThesisApplicationViewModel> applications = mapperUtil.mapList(
                thesisApplicationService.findBySupervisorId(id), ThesisApplicationViewModel.class);
        model.addAttribute("thesesApplications", applications);
        model.addAttribute("statusType", ThesisApplicationStatusType.values());
        return "theses-applications/list";
    }

    @GetMapping("/{id}/defenses")
    public String listThesisDefensesByTeacherId(@PathVariable("id") Long id, Model model) {
        List<ThesisDefenseViewModel> defenses = mapperUtil
                .mapList(thesisDefenseService.findAll(), ThesisDefenseViewModel.class);
        model.addAttribute("defenses", defenses);
        return "theses-defenses/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("teacher", new CreateTeacherViewModel());
        model.addAttribute("departments", mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
        return "teachers/form";
    }

    @PostMapping
    public String createTeacher(
            @Valid @ModelAttribute("teacher") CreateTeacherViewModel teacher,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
            return "teachers/form";
        }

        try {
            CreateTeacherDto dto = mapperUtil.getModelMapper().map(teacher, CreateTeacherDto.class);
            teacherService.save(dto);
            redirectAttributes.addFlashAttribute("success", "Teacher created successfully!");
            return "redirect:/teachers";
        } catch (Exception e) {
            model.addAttribute("departments", mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
            model.addAttribute("error", "Failed to create teacher: " + e.getMessage());
            return "teachers/form";
        }
    }

  @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TeacherViewModel existing = mapperUtil.getModelMapper()
                .map(teacherService.findById(id), TeacherViewModel.class);

        UpdateTeacherViewModel formModel = new UpdateTeacherViewModel();
        formModel.setName(existing.getName());
        formModel.setEmail(existing.getEmail());
        formModel.setAcademicTitle(existing.getAcademicTitle());
        formModel.setDepartmentId(existing.getDepartment().getId());

        model.addAttribute("teacher", formModel);
        model.addAttribute("teacherId", id);
        model.addAttribute("departments",
                mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));

        return "teachers/update";
    }

    @PutMapping("/{id}")
    public String updateTeacher(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("teacher") UpdateTeacherViewModel teacher,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments",
                    mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
            return "teachers/update";
        }

        try {
            UpdateTeacherDto dto = mapperUtil.getModelMapper().map(teacher, UpdateTeacherDto.class);
            teacherService.update(id, dto);

            redirectAttributes.addFlashAttribute("success", "Teacher updated successfully!");
            return "redirect:/teachers";
        } catch (Exception e) {
            model.addAttribute("departments",
                    mapperUtil.mapList(departmentService.findAll(), DepartmentViewModel.class));
            model.addAttribute("error", "Failed to update teacher: " + e.getMessage());
            return "teachers/update";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            this.teacherService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Teacher deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete teacher: " + e.getMessage());
        }
        return "redirect:/teachers";
    }
}
