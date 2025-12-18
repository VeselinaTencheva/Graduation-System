package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.thesis.ThesisDto;
import com.nbu.Graduation_System.dto.thesis_defense.*;
import com.nbu.Graduation_System.dto.defense_session.*;
import com.nbu.Graduation_System.service.teacher.TeacherService;
import com.nbu.Graduation_System.service.defense_session.DefenseSessionService;
import com.nbu.Graduation_System.service.thesis.ThesisDefenseService;
import com.nbu.Graduation_System.service.thesis.ThesisService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;
import com.nbu.Graduation_System.viewmodel.defense_session.*;
import com.nbu.Graduation_System.viewmodel.department.DepartmentViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_defense.ThesisDefenseViewModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/defense-sessions")
@RequiredArgsConstructor
public class DefenseSessionViewController {

    private final DefenseSessionService defenseSessionService;
    private final ThesisDefenseService thesisDefenseService;
    private final TeacherService teacherService;
    private final ThesisService thesisService;
    private final MapperUtil mapperUtil;

    // 🔹 roles: TEACHER, DEAN, ADMIN – can see list of sessions
    @PreAuthorize("hasAnyRole('TEACHER','DEAN','ADMIN')")
    @GetMapping
    public String listSessions(Model model) {
        List<DefenseSessionViewModel> sessions = mapperUtil.mapList(
                defenseSessionService.findAll(),
                DefenseSessionViewModel.class
        );
        model.addAttribute("sessions", sessions);
        return "defense-sessions/list";
    }

    // 🔹 roles: TEACHER, DEAN, ADMIN – view details for one session
    @PreAuthorize("hasAnyRole('TEACHER','DEAN','ADMIN')")
    @GetMapping("/{id}")
    public String viewSession(@PathVariable("id") Long id, Model model) {
        DefenseSessionViewModel session = mapperUtil.getModelMapper().map(
                defenseSessionService.findById(id), DefenseSessionViewModel.class);
        model.addAttribute("session", session);
        System.out.println(session);
        return "defense-sessions/view";
    }

    // @PreAuthorize("hasAnyRole('DEAN','ADMIN')")
    // @GetMapping("/new")
    // public String showCreateForm(Model model) {
    //     CreateDefenseSessionViewModel form = new CreateDefenseSessionViewModel();

    //     List<TeacherViewModel> teachers = mapperUtil.mapList(
    //             teacherService.findAll(),
    //             TeacherViewModel.class
    //     );

    //     // For now – all theses that do NOT yet have defense
    //     List<ThesisViewModel> theses = mapperUtil.mapList(
    //             thesisService.findAllWithoutDefense(),  // you can implement this or filter in code
    //             ThesisViewModel.class
    //     );

    //     model.addAttribute("session", form);
    //     model.addAttribute("teachers", teachers);
    //     model.addAttribute("theses", theses);

    //     return "defenses/form";
    // }

    // @PreAuthorize("hasAnyRole('DEAN','ADMIN')")
    // @PostMapping
    // public String createSession(
    //         @Valid @ModelAttribute("session") CreateDefenseSessionViewModel form,
    //         BindingResult bindingResult,
    //         RedirectAttributes redirectAttributes,
    //         Model model) {

    //     if (bindingResult.hasErrors()) {
    //         List<TeacherViewModel> teachers = mapperUtil.mapList(
    //                 teacherService.findAll(),
    //                 TeacherViewModel.class
    //         );
    //         List<ThesisViewModel> theses = mapperUtil.mapList(
    //                 thesisService.findAllWithoutDefense(),
    //                 ThesisViewModel.class
    //         );
    //         model.addAttribute("teachers", teachers);
    //         model.addAttribute("theses", theses);
    //         return "defenses/form";
    //     }

    //     try {
    //         CreateDefenseSessionDto dto = mapperUtil.getModelMapper().map(form, CreateDefenseSessionDto.class);
    //         defenseSessionService.createSession(dto);
    //         redirectAttributes.addFlashAttribute("success", "Defense session created successfully!");
    //         return "redirect:/defenses";
    //     } catch (Exception e) {
    //         List<TeacherViewModel> teachers = mapperUtil.mapList(
    //                 teacherService.findAll(),
    //                 TeacherViewModel.class
    //         );
    //         List<ThesisViewModel> theses = mapperUtil.mapList(
    //                 thesisService.findAllWithoutDefense(),
    //                 ThesisViewModel.class
    //         );
    //         model.addAttribute("teachers", teachers);
    //         model.addAttribute("theses", theses);
    //         model.addAttribute("error", "Failed to create defense session: " + e.getMessage());
    //         return "defenses/form";
    //     }
    // }

    // 🔹 roles: TEACHER, DEAN, ADMIN – show grade form for one thesis defense
    // @PreAuthorize("hasAnyRole('TEACHER','DEAN','ADMIN')")
    // @GetMapping("/{sessionId}/grade/{defenseId}")
    // public String showGradeForm(
    //         @PathVariable("sessionId") Long sessionId,
    //         @PathVariable("defenseId") Long defenseId,
    //         Model model) {

    //     ThesisDefenseViewModel defense = mapperUtil.getModelMapper().map(
    //             thesisDefenseService.findById(defenseId),
    //             ThesisDefenseViewModel.class
    //     );

    //     GradeDefenseViewModel form = new GradeDefenseViewModel();
    //     form.setGrade(defense.getGrade()); // may be null

    //     model.addAttribute("gradeForm", form);
    //     model.addAttribute("defense", defense);
    //     model.addAttribute("sessionId", sessionId);

    //     return "defenses/grade";
    // }

    // 🔹 roles: TEACHER, DEAN, ADMIN – submit grade
    // @PreAuthorize("hasAnyRole('TEACHER','DEAN','ADMIN')")
    // @PostMapping("/{sessionId}/grade/{defenseId}")
    // public String submitGrade(
    //         @PathVariable("sessionId") Long sessionId,
    //         @PathVariable("defenseId") Long defenseId,
    //         @Valid @ModelAttribute("gradeForm") GradeDefenseViewModel form,
    //         BindingResult bindingResult,
    //         RedirectAttributes redirectAttributes,
    //         Model model) {

    //     if (bindingResult.hasErrors()) {
    //         ThesisDefenseViewModel defense = mapperUtil.getModelMapper().map(
    //                 thesisDefenseService.findById(defenseId),
    //                 ThesisDefenseViewModel.class
    //         );
    //         model.addAttribute("defense", defense);
    //         model.addAttribute("sessionId", sessionId);
    //         return "defenses/grade";
    //     }

    //     try {
    //         thesisDefenseService.updateGrade(defenseId, form.getGrade());
    //         redirectAttributes.addFlashAttribute("success", "Grade saved successfully!");
    //     } catch (Exception e) {
    //         redirectAttributes.addFlashAttribute("error", "Failed to save grade: " + e.getMessage());
    //     }

    //     return "redirect:/defenses/" + sessionId;
    // }
}
