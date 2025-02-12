package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.service.thesis.ThesisDefenseService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.thesis_defense.ThesisDefenseViewModel;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/thesis-defenses")
@AllArgsConstructor
public class ThesisDefenseController {
    
    private final ThesisDefenseService thesisDefenseService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String listThesesDefenses(Model model) {
        List<ThesisDefenseViewModel> defenses = mapperUtil
                .mapList(thesisDefenseService.findAll(), ThesisDefenseViewModel.class);
        model.addAttribute("defenses", defenses);
        return "theses-defenses/list";
    }

    @GetMapping("/{id}")
    public String viewDefense(@PathVariable Long id, Model model) {
        ThesisDefenseViewModel defense = mapperUtil.getModelMapper().map(
                thesisDefenseService.findById(id), ThesisDefenseViewModel.class);
        model.addAttribute("defense", defense);
        return "theses-defenses/view";
    }

    @GetMapping("/teacher/{id}")
    public String listDefensesByTeacherId(@PathVariable Long id, Model model) {
        List<ThesisDefenseViewModel> defenses = mapperUtil.mapList(
                thesisDefenseService.findByCommitteeMemberId(id), ThesisDefenseViewModel.class);
        model.addAttribute("defenses", defenses);
        return "theses-defenses/list";
    }
}
