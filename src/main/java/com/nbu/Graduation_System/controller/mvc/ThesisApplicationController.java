package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis_application.CreateThesisApplicationDto;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.service.student.StudentService;
import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.util.SecurityUtils;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_application.CreateThesisApplicationViewModel;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/thesis-applications")
@AllArgsConstructor
public class ThesisApplicationController {
    private final ThesisApplicationService thesisApplicationService;
    private final StudentService studentService;
    private final MapperUtil mapperUtil;
    private final SecurityUtils securityUtils;
    
    @GetMapping
    public String listThesesApplications(Model model) {
        List<ThesisApplicationViewModel> applications = mapperUtil.mapList(
                thesisApplicationService.findAll(), ThesisApplicationViewModel.class);
        model.addAttribute("thesesApplications", applications);
        model.addAttribute("statusType", ThesisApplicationStatusType.values());
        return "theses-applications/list";
    }

    @GetMapping("/{id}")
    public String viewThesisApplication(@PathVariable("id") Long id, Model model) {
        ThesisApplicationViewModel thesisApp = mapperUtil.getModelMapper().map(
                thesisApplicationService.findById(id), ThesisApplicationViewModel.class);
        model.addAttribute("thesisApp", thesisApp);
        model.addAttribute("statusType", ThesisApplicationStatusType.values());
        return "theses-applications/view";
    }

    @GetMapping("/new")
    public String newThesisApplication(Model model) {
        TeacherViewModel teacher = securityUtils.getCurrentTeacher();

        model.addAttribute("thesisApp", new CreateThesisApplicationViewModel());
        model.addAttribute("students", mapperUtil.mapList(
            studentService.findAllEligibleForThesisApplicationByDepartment(teacher.getDepartment().getId()), 
            StudentViewModel.class
        ));
        return "theses-applications/form";
    }

    @PostMapping
    public String createThesisApplication(
            @Valid @ModelAttribute("thesisApp") CreateThesisApplicationViewModel thesisApp,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        TeacherViewModel teacher = securityUtils.getCurrentTeacher();

        // Add students from the same department for form re-display in case of errors
        model.addAttribute("students", mapperUtil.mapList(
            studentService.findAllEligibleForThesisApplicationByDepartment(teacher.getDepartment().getId()), 
            StudentViewModel.class
        ));
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Please correct the errors below");
            return "theses-applications/form";
        }

        try {
            // Map to DTO and set additional fields
            CreateThesisApplicationDto dto = mapperUtil.getModelMapper().map(thesisApp, CreateThesisApplicationDto.class);
            dto.setSupervisor(mapperUtil.getModelMapper().map(teacher, TeacherDto.class));
            dto.setDepartment(teacher.getDepartment());
            
            thesisApplicationService.create(dto);
            redirectAttributes.addFlashAttribute("success", "Thesis application submitted successfully!");
            return "redirect:/thesis-applications";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to submit thesis application: " + e.getMessage());
            return "theses-applications/form";
        }
    }

    @PostMapping("/{id}/approve")
    public String approveThesisApplication(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {
        try {
            thesisApplicationService.approve(id);
            redirectAttributes.addFlashAttribute("success", "Thesis application approved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to approve thesis application: " + e.getMessage());
        }
        return "redirect:/thesis-applications/" + id;
    }

    @PostMapping("/{id}/reject")
    public String rejectThesisApplication(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {
        try {
            thesisApplicationService.reject(id);
            redirectAttributes.addFlashAttribute("success", "Thesis application rejected successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to reject thesis application: " + e.getMessage());
        }
        return "redirect:/thesis-applications/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.thesisApplicationService.deleteById(id);
        return "redirect:/thesis-applications";
    }


    // @GetMapping("by-student/{id}")
    // public String listThesesApplicationsByStudentId(@PathVariable Long id, Model model) {
    //     model.addAttribute("thesis", mapperUtil.getModelMapper().map(
    //             thesisApplicationService.findByStudentId(id), ThesisApplicationViewModel.class));
    //     return "thesesApplications/view";
    // }

    
    // private final ThesisApplicationService thesisApplicationService;

    // public ThesisApplicationController(ThesisApplicationService thesisApplicationService) {
    //     this.thesisApplicationService = thesisApplicationService;
    // }

    // @GetMapping
    // public String listApplications(Model model) {
    //     model.addAttribute("applications", thesisApplicationService.findAll());
    //     return "thesis-applications/list";
    // }

    // @GetMapping("/{id}")
    // public String viewApplication(@PathVariable Long id, Model model) {
    //     return thesisApplicationService.findById(id)
    //             .map(application -> {
    //                 model.addAttribute("application", application);
    //                 return "thesis-applications/view";
    //             })
    //             .orElse("redirect:/thesis-applications");
    // }

    // @GetMapping("/student/{studentId}")
    // public String listStudentApplications(@PathVariable Long studentId, Model model) {
    //     model.addAttribute("applications", thesisApplicationService.findByStudentId(studentId));
    //     model.addAttribute("studentId", studentId);
    //     return "thesis-applications/student-list";
    // }

    // @GetMapping("/supervisor/{supervisorId}")
    // public String listSupervisorApplications(@PathVariable Long supervisorId, Model model) {
    //     model.addAttribute("applications", thesisApplicationService.findBySupervisorId(supervisorId));
    //     model.addAttribute("supervisorId", supervisorId);
    //     return "thesis-applications/supervisor-list";
    // }

    // @GetMapping("/new")
    // public String newApplicationForm(Model model) {
    //     model.addAttribute("application", new ThesisApplicationDto());
    //     return "thesis-applications/form";
    // }

    // @PostMapping
    // public String createApplication(@ModelAttribute ThesisApplicationDto applicationDto, 
    //                               RedirectAttributes redirectAttributes) {
    //     ThesisApplicationDto savedApplication = thesisApplicationService.save(applicationDto);
    //     redirectAttributes.addFlashAttribute("message", "Thesis application created successfully");
    //     return "redirect:/thesis-applications/" + savedApplication.getId();
    // }

    // @GetMapping("/{id}/edit")
    // public String editApplicationForm(@PathVariable Long id, Model model) {
    //     return thesisApplicationService.findById(id)
    //             .map(application -> {
    //                 model.addAttribute("application", application);
    //                 return "thesis-applications/form";
    //             })
    //             .orElse("redirect:/thesis-applications");
    // }

    // @PostMapping("/{id}")
    // public String updateApplication(@PathVariable Long id, 
    //                               @ModelAttribute ThesisApplicationDto applicationDto,
    //                               RedirectAttributes redirectAttributes) {
    //     if (!thesisApplicationService.existsById(id)) {
    //         return "redirect:/thesis-applications";
    //     }
    //     applicationDto.setId(id);
    //     thesisApplicationService.save(applicationDto);
    //     redirectAttributes.addFlashAttribute("message", "Thesis application updated successfully");
    //     return "redirect:/thesis-applications/" + id;
    // }

    // @PostMapping("/{id}/status")
    // public String updateApplicationStatus(@PathVariable Long id,
    //                                     @RequestParam ThesisApplicationStatusType status,
    //                                     RedirectAttributes redirectAttributes) {
    //     thesisApplicationService.updateStatus(id, status);
    //     redirectAttributes.addFlashAttribute("message", "Application status updated successfully");
    //     return "redirect:/thesis-applications/" + id;
    // }

    // @PostMapping("/{id}/delete")
    // public String deleteApplication(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    //     if (thesisApplicationService.existsById(id)) {
    //         thesisApplicationService.deleteById(id);
    //         redirectAttributes.addFlashAttribute("message", "Thesis application deleted successfully");
    //     }
    //     return "redirect:/thesis-applications";
    // }
}
