package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.service.thesis.ThesisReviewService;
import com.nbu.Graduation_System.service.thesis.ThesisService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.util.SecurityUtils;
import com.nbu.Graduation_System.viewmodel.thesis_review.ThesisReviewViewModel;

import jakarta.validation.Valid;

import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_review.CreateThesisReviewViewModel;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;
import com.nbu.Graduation_System.dto.thesis_review.CreateThesisReviewDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("/thesis-reviews")
public class ThesisReviewController {
    
    private final ThesisReviewService thesisReviewService;
    private final ThesisService thesisService;
    private final MapperUtil mapperUtil;
    private final SecurityUtils securityUtils;

    @GetMapping
    public String listThesesReviews(Model model) {
                List<ThesisReviewViewModel> reviews = mapperUtil
            .mapList(this.thesisReviewService.findAll(), ThesisReviewViewModel.class);
        model.addAttribute("reviews", reviews);
    return "theses-reviews/list";
    }

    @GetMapping("/{id}")
    public String viewReview(@PathVariable("id") Long id, Model model) {
        model.addAttribute("review", mapperUtil.getModelMapper().map(
                thesisReviewService.findById(id), ThesisReviewViewModel.class));
        return "theses-reviews/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            this.thesisReviewService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Review deleted successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/thesis-reviews";
    }

    @GetMapping("/new")
    public String newReviewForm(@RequestParam(value = "thesisId", required = false, defaultValue = "-1") Long thesisId, Model model) {
        if (thesisId == -1) {
            return "error";
        }
    
        CreateThesisReviewViewModel review = new CreateThesisReviewViewModel();
        TeacherViewModel teacher = securityUtils.getCurrentTeacher();
        ThesisViewModel thesis = mapperUtil.getModelMapper().map(thesisService.findById(thesisId), ThesisViewModel.class);
        model.addAttribute("thesis", thesis);
        model.addAttribute("reviewer", teacher);
        model.addAttribute("review", review);
        return "theses-reviews/form";
    }

    @PostMapping("/new")
    public String createReview(
            @Valid @ModelAttribute("review") CreateThesisReviewViewModel reviewViewModel,
            BindingResult bindingResult,
            @RequestParam(value = "thesisId", required = true) Long thesisId,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            TeacherViewModel teacher = securityUtils.getCurrentTeacher();
            model.addAttribute("reviewer", teacher);
            return "theses-reviews/form";
        }
        
        TeacherDto teacher = mapperUtil.getModelMapper().map(securityUtils.getCurrentTeacher(), TeacherDto.class);

        CreateThesisReviewDto dto = mapperUtil.getModelMapper().map(
                reviewViewModel, CreateThesisReviewDto.class);
        dto.setReviewer(teacher);

        ThesisDto thesis = mapperUtil.getModelMapper().map(
                thesisService.findById(thesisId), ThesisDto.class
        );
        dto.setThesis(thesis);

        ThesisReviewViewModel savedReview = mapperUtil.getModelMapper().map(
                thesisReviewService.save(dto), ThesisReviewViewModel.class);
            
        redirectAttributes.addFlashAttribute("message", "Review created successfully");
        return "redirect:/thesis-reviews/" + savedReview.getId();
    }

    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable("id") Long id, Model model) {
        ThesisReviewViewModel review = mapperUtil.getModelMapper().map(
                thesisReviewService.findById(id), ThesisReviewViewModel.class
        );
        
        if (review == null) {
            throw new RuntimeException("Review not found for thesis with id=" + id);     }
        
        model.addAttribute("review", review);
        return "theses-reviews/update-form";
    }

    @PostMapping("/edit/{id}")
    public String editReview(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("review") ThesisReviewViewModel reviewViewModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            ThesisReviewViewModel existingReview = mapperUtil.getModelMapper().map(
                thesisReviewService.findById(id), 
                ThesisReviewViewModel.class
            );
            
            reviewViewModel.setReviewer(existingReview.getReviewer());
            reviewViewModel.setThesis(existingReview.getThesis());
            reviewViewModel.setReviewDate(existingReview.getReviewDate());
            
            model.addAttribute("error", "Please correct the validation errors below");
            return "theses-reviews/update-form";
        }
        
        CreateThesisReviewDto dto = new CreateThesisReviewDto();
        dto.setComments(reviewViewModel.getComments());
        dto.setPositive(reviewViewModel.isPositive());
        
        ThesisReviewDto savedReview = thesisReviewService.update(
            id, 
            dto
        );
            
        redirectAttributes.addFlashAttribute("message", "Review updated successfully");
        return "redirect:/thesis-reviews/" + savedReview.getId();
    }


    // @GetMapping("/{id}")
    // public String viewReview(@PathVariable Long id, Model model) {
    //     return thesisReviewService.findById(id)
    //             .map(review -> {
    //                 model.addAttribute("review", review);
    //                 return "thesis-reviews/view";
    //             })
    //             .orElse("redirect:/thesis-reviews");
    // }

    // @GetMapping("/thesis/{thesisId}")
    // public String listThesisReviews(@PathVariable Long thesisId, Model model) {
    //     model.addAttribute("reviews", thesisReviewService.findByThesisId(thesisId));
    //     model.addAttribute("thesisId", thesisId);
    //     return "thesis-reviews/thesis-list";
    // }

    // @GetMapping("/reviewer/{reviewerId}")
    // public String listReviewerReviews(@PathVariable Long reviewerId, Model model) {
    //     model.addAttribute("reviews", thesisReviewService.findByReviewerId(reviewerId));
    //     model.addAttribute("reviewerId", reviewerId);
    //     return "thesis-reviews/reviewer-list";
    // }

    // @GetMapping("/submit")
    // public String submitReviewForm(@RequestParam(required = false) Long thesisId,
    //                              @RequestParam(required = false) Long reviewerId,
    //                              Model model) {
    //     model.addAttribute("thesisId", thesisId);
    //     model.addAttribute("reviewerId", reviewerId);
    //     return "thesis-reviews/submit-form";
    // }

    // @PostMapping("/submit")
    // public String submitReview(@RequestParam Long thesisId,
    //                          @RequestParam Long reviewerId,
    //                          @RequestParam String content,
    //                          @RequestParam boolean isPositive,
    //                          RedirectAttributes redirectAttributes) {
    //     ThesisReviewDto review = thesisReviewService.submitReview(thesisId, reviewerId, content, isPositive);
    //     redirectAttributes.addFlashAttribute("message", "Review submitted successfully");
    //     return "redirect:/thesis-reviews/" + review.getId();
    // }

    // @PostMapping
    // public String createReview(@ModelAttribute ThesisReviewDto reviewDto, RedirectAttributes redirectAttributes) {
    //     ThesisReviewDto savedReview = thesisReviewService.save(reviewDto);
    //     redirectAttributes.addFlashAttribute("message", "Review created successfully");
    //     return "redirect:/thesis-reviews/" + savedReview.getId();
    // }

    // @GetMapping("/{id}/edit")
    // public String editReviewForm(@PathVariable Long id, Model model) {
    //     return thesisReviewService.findById(id)
    //             .map(review -> {
    //                 model.addAttribute("review", review);
    //                 return "thesis-reviews/form";
    //             })
    //             .orElse("redirect:/thesis-reviews");
    // }

    // @PostMapping("/{id}")
    // public String updateReview(@PathVariable Long id, @ModelAttribute ThesisReviewDto reviewDto, 
    //                          RedirectAttributes redirectAttributes) {
    //     if (!thesisReviewService.existsById(id)) {
    //         return "redirect:/thesis-reviews";
    //     }
    //     reviewDto.setId(id);
    //     thesisReviewService.save(reviewDto);
    //     redirectAttributes.addFlashAttribute("message", "Review updated successfully");
    //     return "redirect:/thesis-reviews/" + id;
    // }

    // @PostMapping("/{id}/delete")
    // public String deleteReview(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    //     if (thesisReviewService.existsById(id)) {
    //         thesisReviewService.deleteById(id);
    //         redirectAttributes.addFlashAttribute("message", "Review deleted successfully");
    //     }
    //     return "redirect:/thesis-reviews";
    // }
}
