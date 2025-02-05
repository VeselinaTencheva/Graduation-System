package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.service.thesis.ThesisDefenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.Set;

@Controller
@RequestMapping("/thesis-defenses")
public class ThesisDefenseController {
    
    private final ThesisDefenseService thesisDefenseService;

    public ThesisDefenseController(ThesisDefenseService thesisDefenseService) {
        this.thesisDefenseService = thesisDefenseService;
    }

    @GetMapping
    public String listDefenses(Model model) {
        model.addAttribute("defenses", thesisDefenseService.findAll());
        return "thesis-defenses/list";
    }

    @GetMapping("/{id}")
    public String viewDefense(@PathVariable Long id, Model model) {
        return thesisDefenseService.findById(id)
                .map(defense -> {
                    model.addAttribute("defense", defense);
                    return "thesis-defenses/view";
                })
                .orElse("redirect:/thesis-defenses");
    }

    @GetMapping("/new")
    public String newDefenseForm(Model model) {
        model.addAttribute("defense", new ThesisDefenseDto());
        return "thesis-defenses/form";
    }

    @GetMapping("/schedule")
    public String scheduleDefenseForm(@RequestParam(required = false) Long thesisId, Model model) {
        model.addAttribute("thesisId", thesisId);
        return "thesis-defenses/schedule-form";
    }

    @PostMapping("/schedule")
    public String scheduleDefense(@RequestParam Long thesisId,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime defenseDate,
                                @RequestParam Set<Teacher> committee,
                                RedirectAttributes redirectAttributes) {
        ThesisDefenseDto defense = thesisDefenseService.scheduleDefense(thesisId, defenseDate, committee);
        redirectAttributes.addFlashAttribute("message", "Defense scheduled successfully");
        return "redirect:/thesis-defenses/" + defense.getId();
    }

    @PostMapping
    public String createDefense(@ModelAttribute ThesisDefenseDto defenseDto, RedirectAttributes redirectAttributes) {
        ThesisDefenseDto savedDefense = thesisDefenseService.save(defenseDto);
        redirectAttributes.addFlashAttribute("message", "Defense created successfully");
        return "redirect:/thesis-defenses/" + savedDefense.getId();
    }

    @GetMapping("/{id}/edit")
    public String editDefenseForm(@PathVariable Long id, Model model) {
        return thesisDefenseService.findById(id)
                .map(defense -> {
                    model.addAttribute("defense", defense);
                    return "thesis-defenses/form";
                })
                .orElse("redirect:/thesis-defenses");
    }

    @PostMapping("/{id}")
    public String updateDefense(@PathVariable Long id, @ModelAttribute ThesisDefenseDto defenseDto, 
                              RedirectAttributes redirectAttributes) {
        if (!thesisDefenseService.existsById(id)) {
            return "redirect:/thesis-defenses";
        }
        defenseDto.setId(id);
        thesisDefenseService.save(defenseDto);
        redirectAttributes.addFlashAttribute("message", "Defense updated successfully");
        return "redirect:/thesis-defenses/" + id;
    }

    @PostMapping("/{id}/grade")
    public String gradeDefense(@PathVariable Long id,
                             @RequestParam Double grade,
                             RedirectAttributes redirectAttributes) {
        thesisDefenseService.gradeDefense(id, grade);
        redirectAttributes.addFlashAttribute("message", "Defense graded successfully");
        return "redirect:/thesis-defenses/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteDefense(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (thesisDefenseService.existsById(id)) {
            thesisDefenseService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Defense deleted successfully");
        }
        return "redirect:/thesis-defenses";
    }
}
