// package com.nbu.Graduation_System.controller.mvc;

// import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
// import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
// import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @Controller
// @RequestMapping("/thesis-applications")
// public class ThesisApplicationController {
    
//     private final ThesisApplicationService thesisApplicationService;

//     public ThesisApplicationController(ThesisApplicationService thesisApplicationService) {
//         this.thesisApplicationService = thesisApplicationService;
//     }

//     @GetMapping
//     public String listApplications(Model model) {
//         model.addAttribute("applications", thesisApplicationService.findAll());
//         return "thesis-applications/list";
//     }

//     @GetMapping("/{id}")
//     public String viewApplication(@PathVariable Long id, Model model) {
//         return thesisApplicationService.findById(id)
//                 .map(application -> {
//                     model.addAttribute("application", application);
//                     return "thesis-applications/view";
//                 })
//                 .orElse("redirect:/thesis-applications");
//     }

//     @GetMapping("/student/{studentId}")
//     public String listStudentApplications(@PathVariable Long studentId, Model model) {
//         model.addAttribute("applications", thesisApplicationService.findByStudentId(studentId));
//         model.addAttribute("studentId", studentId);
//         return "thesis-applications/student-list";
//     }

//     @GetMapping("/supervisor/{supervisorId}")
//     public String listSupervisorApplications(@PathVariable Long supervisorId, Model model) {
//         model.addAttribute("applications", thesisApplicationService.findBySupervisorId(supervisorId));
//         model.addAttribute("supervisorId", supervisorId);
//         return "thesis-applications/supervisor-list";
//     }

//     @GetMapping("/new")
//     public String newApplicationForm(Model model) {
//         model.addAttribute("application", new ThesisApplicationDto());
//         return "thesis-applications/form";
//     }

//     @PostMapping
//     public String createApplication(@ModelAttribute ThesisApplicationDto applicationDto, 
//                                   RedirectAttributes redirectAttributes) {
//         ThesisApplicationDto savedApplication = thesisApplicationService.save(applicationDto);
//         redirectAttributes.addFlashAttribute("message", "Thesis application created successfully");
//         return "redirect:/thesis-applications/" + savedApplication.getId();
//     }

//     @GetMapping("/{id}/edit")
//     public String editApplicationForm(@PathVariable Long id, Model model) {
//         return thesisApplicationService.findById(id)
//                 .map(application -> {
//                     model.addAttribute("application", application);
//                     return "thesis-applications/form";
//                 })
//                 .orElse("redirect:/thesis-applications");
//     }

//     @PostMapping("/{id}")
//     public String updateApplication(@PathVariable Long id, 
//                                   @ModelAttribute ThesisApplicationDto applicationDto,
//                                   RedirectAttributes redirectAttributes) {
//         if (!thesisApplicationService.existsById(id)) {
//             return "redirect:/thesis-applications";
//         }
//         applicationDto.setId(id);
//         thesisApplicationService.save(applicationDto);
//         redirectAttributes.addFlashAttribute("message", "Thesis application updated successfully");
//         return "redirect:/thesis-applications/" + id;
//     }

//     @PostMapping("/{id}/status")
//     public String updateApplicationStatus(@PathVariable Long id,
//                                         @RequestParam ThesisApplicationStatusType status,
//                                         RedirectAttributes redirectAttributes) {
//         thesisApplicationService.updateStatus(id, status);
//         redirectAttributes.addFlashAttribute("message", "Application status updated successfully");
//         return "redirect:/thesis-applications/" + id;
//     }

//     @PostMapping("/{id}/delete")
//     public String deleteApplication(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//         if (thesisApplicationService.existsById(id)) {
//             thesisApplicationService.deleteById(id);
//             redirectAttributes.addFlashAttribute("message", "Thesis application deleted successfully");
//         }
//         return "redirect:/thesis-applications";
//     }
// }
