package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.ThesisDto;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.service.thesis.ThesisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/theses")
public class ThesisController {
    
    private final ThesisService thesisService;

    public ThesisController(ThesisService thesisService) {
        this.thesisService = thesisService;
    }

    @GetMapping
    public String listTheses(Model model) {
        model.addAttribute("theses", thesisService.findAll());
        return "theses/list";
    }

    @GetMapping("/{id}")
    public String viewThesis(@PathVariable Long id, Model model) {
        return thesisService.findById(id)
                .map(thesis -> {
                    model.addAttribute("thesis", thesis);
                    return "theses/view";
                })
                .orElse("redirect:/theses");
    }

    @GetMapping("/new")
    public String newThesisForm(Model model) {
        model.addAttribute("thesis", new ThesisDto());
        return "theses/form";
    }

    @PostMapping
    public String createThesis(@ModelAttribute ThesisDto thesisDto, RedirectAttributes redirectAttributes) {
        ThesisDto savedThesis = thesisService.save(thesisDto);
        redirectAttributes.addFlashAttribute("message", "Thesis created successfully");
        return "redirect:/theses/" + savedThesis.getId();
    }

    @PostMapping("/from-application")
    public String createThesisFromApplication(@ModelAttribute ThesisApplication application, 
                                            RedirectAttributes redirectAttributes) {
        ThesisDto savedThesis = thesisService.createFromApplication(application);
        redirectAttributes.addFlashAttribute("message", "Thesis created from application successfully");
        return "redirect:/theses/" + savedThesis.getId();
    }

    @GetMapping("/{id}/edit")
    public String editThesisForm(@PathVariable Long id, Model model) {
        return thesisService.findById(id)
                .map(thesis -> {
                    model.addAttribute("thesis", thesis);
                    return "theses/form";
                })
                .orElse("redirect:/theses");
    }

    @PostMapping("/{id}")
    public String updateThesis(@PathVariable Long id, @ModelAttribute ThesisDto thesisDto, 
                             RedirectAttributes redirectAttributes) {
        if (!thesisService.existsById(id)) {
            return "redirect:/theses";
        }
        thesisDto.setId(id);
        thesisService.save(thesisDto);
        redirectAttributes.addFlashAttribute("message", "Thesis updated successfully");
        return "redirect:/theses/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteThesis(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (thesisService.existsById(id)) {
            thesisService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Thesis deleted successfully");
        }
        return "redirect:/theses";
    }
}
