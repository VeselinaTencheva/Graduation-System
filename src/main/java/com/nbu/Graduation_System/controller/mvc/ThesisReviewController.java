package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.ThesisReviewDto;
import com.nbu.Graduation_System.service.thesis.ThesisReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/thesis-reviews")
public class ThesisReviewController {
    
    private final ThesisReviewService thesisReviewService;

    public ThesisReviewController(ThesisReviewService thesisReviewService) {
        this.thesisReviewService = thesisReviewService;
    }

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", thesisReviewService.findAll());
        return "thesis-reviews/list";
    }

    @GetMapping("/{id}")
    public String viewReview(@PathVariable Long id, Model model) {
        return thesisReviewService.findById(id)
                .map(review -> {
                    model.addAttribute("review", review);
                    return "thesis-reviews/view";
                })
                .orElse("redirect:/thesis-reviews");
    }

    @GetMapping("/thesis/{thesisId}")
    public String listThesisReviews(@PathVariable Long thesisId, Model model) {
        model.addAttribute("reviews", thesisReviewService.findByThesisId(thesisId));
        model.addAttribute("thesisId", thesisId);
        return "thesis-reviews/thesis-list";
    }

    @GetMapping("/reviewer/{reviewerId}")
    public String listReviewerReviews(@PathVariable Long reviewerId, Model model) {
        model.addAttribute("reviews", thesisReviewService.findByReviewerId(reviewerId));
        model.addAttribute("reviewerId", reviewerId);
        return "thesis-reviews/reviewer-list";
    }

    @GetMapping("/new")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new ThesisReviewDto());
        return "thesis-reviews/form";
    }

    @GetMapping("/submit")
    public String submitReviewForm(@RequestParam(required = false) Long thesisId,
                                 @RequestParam(required = false) Long reviewerId,
                                 Model model) {
        model.addAttribute("thesisId", thesisId);
        model.addAttribute("reviewerId", reviewerId);
        return "thesis-reviews/submit-form";
    }

    @PostMapping("/submit")
    public String submitReview(@RequestParam Long thesisId,
                             @RequestParam Long reviewerId,
                             @RequestParam String content,
                             @RequestParam boolean isPositive,
                             RedirectAttributes redirectAttributes) {
        ThesisReviewDto review = thesisReviewService.submitReview(thesisId, reviewerId, content, isPositive);
        redirectAttributes.addFlashAttribute("message", "Review submitted successfully");
        return "redirect:/thesis-reviews/" + review.getId();
    }

    @PostMapping
    public String createReview(@ModelAttribute ThesisReviewDto reviewDto, RedirectAttributes redirectAttributes) {
        ThesisReviewDto savedReview = thesisReviewService.save(reviewDto);
        redirectAttributes.addFlashAttribute("message", "Review created successfully");
        return "redirect:/thesis-reviews/" + savedReview.getId();
    }

    @GetMapping("/{id}/edit")
    public String editReviewForm(@PathVariable Long id, Model model) {
        return thesisReviewService.findById(id)
                .map(review -> {
                    model.addAttribute("review", review);
                    return "thesis-reviews/form";
                })
                .orElse("redirect:/thesis-reviews");
    }

    @PostMapping("/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute ThesisReviewDto reviewDto, 
                             RedirectAttributes redirectAttributes) {
        if (!thesisReviewService.existsById(id)) {
            return "redirect:/thesis-reviews";
        }
        reviewDto.setId(id);
        thesisReviewService.save(reviewDto);
        redirectAttributes.addFlashAttribute("message", "Review updated successfully");
        return "redirect:/thesis-reviews/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (thesisReviewService.existsById(id)) {
            thesisReviewService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Review deleted successfully");
        }
        return "redirect:/thesis-reviews";
    }
}
