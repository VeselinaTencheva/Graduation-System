package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.thesis.CreateThesisDto;
import com.nbu.Graduation_System.service.thesis.ThesisService;
import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.util.SecurityUtils;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.CreateThesisViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/my-thesis")
@AllArgsConstructor
public class MyThesisController {
    
    private final ThesisService thesisService;
    private final ThesisApplicationService thesisApplicationService;
    private final SecurityUtils securityUtils;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String viewMyThesis(Model model) {
        StudentViewModel student = securityUtils.getCurrentStudent();
        
        // Get the student's approved thesis application
        List<ThesisApplicationViewModel> applications = mapperUtil.mapList(
            thesisApplicationService.findByStudentId(student.getId()),
            ThesisApplicationViewModel.class
        );
        
        ThesisApplicationViewModel approvedApplication = applications.stream()
            .filter(ThesisApplicationViewModel::isApproved)
            .findFirst()
            .orElse(null);
            
        if (approvedApplication != null) {
            model.addAttribute("thesisApplication", approvedApplication);
            if (approvedApplication.getThesis() != null) {
                model.addAttribute("thesis", mapperUtil.getModelMapper().map(
                    thesisService.findById(approvedApplication.getThesis().getId()),
                    ThesisViewModel.class
                ));
            }
        }
        
        return "my-thesis/view";
    }

    @GetMapping("/submit")
    public String newThesisForm(@RequestParam("applicationId") Long applicationId, Model model) {
        StudentViewModel student = securityUtils.getCurrentStudent();
        ThesisApplicationViewModel application = mapperUtil.getModelMapper().map(
            thesisApplicationService.findById(applicationId),
            ThesisApplicationViewModel.class
        );
        
        // Verify this is the student's application and it's approved
        if (!application.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("Unauthorized access to thesis application");
        }
        if (!application.isApproved()) {
            throw new RuntimeException("Cannot submit thesis for unapproved application");
        }
        if (application.getThesis() != null) {
            throw new RuntimeException("Thesis has already been submitted");
        }
        
        CreateThesisViewModel thesis = new CreateThesisViewModel();
        thesis.setApplicationId(applicationId);
        
        model.addAttribute("thesis", thesis);
        model.addAttribute("app", application);
        
        System.out.println("Model attributes: " + model.asMap());
        
        return "my-thesis/form";
    }

    @PostMapping("/submit/{applicationId}")
    public String submitThesis(
            @PathVariable("applicationId") Long applicationId,
            @Valid @ModelAttribute("thesis") CreateThesisViewModel thesis,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        StudentViewModel student = securityUtils.getCurrentStudent();
        ThesisApplicationViewModel application = mapperUtil.getModelMapper().map(
            thesisApplicationService.findById(applicationId),
            ThesisApplicationViewModel.class
        );
        
        // Debug info
        System.out.println("POST - Application: " + application);
        System.out.println("POST - Application ID: " + applicationId);
        System.out.println("POST - Student: " + student);
        
        // Verify this is the student's application and it's approved
        if (!application.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("Unauthorized access to thesis application");
        }
        if (!application.isApproved()) {
            throw new RuntimeException("Cannot submit thesis for unapproved application");
        }
        if (application.getThesis() != null) {
            throw new RuntimeException("Thesis has already been submitted");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("application", application);
            return "my-thesis/form";
        }

        try {
            CreateThesisDto dto = mapperUtil.getModelMapper().map(thesis, CreateThesisDto.class);
            dto.setApplicationId(applicationId);
            System.out.println("DTO: " + dto);
            thesisService.save(dto);
            redirectAttributes.addFlashAttribute("success", "Thesis submitted successfully!");
            return "redirect:/my-thesis";
        } catch (Exception e) {
            model.addAttribute("application", application);
            model.addAttribute("error", "Failed to submit thesis: " + e.getMessage());
            return "my-thesis/form";
        }
    }
}
