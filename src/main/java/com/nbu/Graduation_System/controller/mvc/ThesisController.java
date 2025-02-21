package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.service.thesis.ThesisService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.util.SecurityUtils;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/theses")
public class ThesisController {
    
    private final ThesisService thesisService;
    private final MapperUtil mapperUtil;
    private final SecurityUtils securityUtils;

   
    @GetMapping
    public String listTheses(Model model) {
        List<ThesisViewModel> theses;
        
        if (securityUtils.isStudent()) {
            StudentViewModel currentStudent = securityUtils.getCurrentStudent();
            theses = mapperUtil.mapList(
                this.thesisService.findAll().stream()
                    .filter(thesis -> thesis.getThesisApplication().getStudent().getId().equals(currentStudent.getId()))
                    .toList(), 
                ThesisViewModel.class
            );
        } else {
            theses = mapperUtil.mapList(this.thesisService.findAll(), ThesisViewModel.class);
        }
        
        model.addAttribute("theses", theses);
        return "/theses/list";
    }

    @GetMapping("/{id}")
    public String viewThesis(@PathVariable("id") Long id, Model model) {
        ThesisViewModel thesis = mapperUtil.getModelMapper().map(
            thesisService.findById(id), ThesisViewModel.class);
        
        // If user is a student, verify ownership
        if (securityUtils.isStudent()) {
            StudentViewModel currentStudent = securityUtils.getCurrentStudent();
            if (!thesis.getThesisApplication().getStudent().getId().equals(currentStudent.getId())) {
                return "redirect:/unauthorized";
            }
        }
        
        model.addAttribute("thesis", thesis);
        return "theses/view";
    }

    // @GetMapping("/new")
    // public String newThesisForm(Model model) {
    //     model.addAttribute("thesis", new CreateThesisViewModel());
    //     return "theses/form";
    // }

    // @PostMapping
    // public String createThesis(@Valid @ModelAttribute("thesis") CreateThesisViewModel thesis,
    //                          BindingResult bindingResult,
    //                          RedirectAttributes redirectAttributes) {
    //     if (bindingResult.hasErrors()) {
    //         return "theses/form";
    //     }
    //     ThesisDto savedThesis = thesisService.save(
    //             mapperUtil.getModelMapper().map(thesis, CreateThesisDto.class));
    //     redirectAttributes.addFlashAttribute("message", "Thesis created successfully");
    //     return "redirect:/theses/" + savedThesis.getId();
    // }

    // @PostMapping("/from-application")
    // public String createThesisFromApplication(@ModelAttribute ThesisApplication application,
    //                                         RedirectAttributes redirectAttributes) {
    //     ThesisDto savedThesis = thesisService.createFromApplication(application);
    //     redirectAttributes.addFlashAttribute("message", "Thesis created from application successfully");
    //     return "redirect:/theses/" + savedThesis.getId();
    // }

    // @GetMapping("/{id}/edit")
    // public String editThesisForm(@PathVariable Long id, Model model) {
    //     model.addAttribute("thesis", mapperUtil.getModelMapper().map(
    //             thesisService.findById(id), CreateThesisViewModel.class));
    //     return "theses/form";
    // }

    // @PostMapping("/{id}")
    // public String updateThesis(@PathVariable Long id,
    //                          @Valid @ModelAttribute("thesis") CreateThesisViewModel thesis,
    //                          BindingResult bindingResult,
    //                          RedirectAttributes redirectAttributes) {
    //     if (bindingResult.hasErrors()) {
    //         return "theses/form";
    //     }
    //     if (!thesisService.existsById(id)) {
    //         return "redirect:/theses";
    //     }
    //     CreateThesisDto thesisDto = mapperUtil.getModelMapper().map(thesis, CreateThesisDto.class);
    //     thesisService.save(thesisDto);
    //     redirectAttributes.addFlashAttribute("message", "Thesis updated successfully");
    //     return "redirect:/theses/" + id;
    // }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.thesisService.deleteById(id);
        return "redirect:/theses";
    }
}
