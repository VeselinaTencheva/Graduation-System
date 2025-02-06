package com.nbu.Graduation_System.controller.api;

import com.nbu.Graduation_System.dto.ThesisReviewDto;
import com.nbu.Graduation_System.service.thesis.ThesisReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/thesis-reviews")
public class ThesisReviewApiController {
    
    private final ThesisReviewService thesisReviewService;

    public ThesisReviewApiController(ThesisReviewService thesisReviewService) {
        this.thesisReviewService = thesisReviewService;
    }

    @GetMapping
    public ResponseEntity<List<ThesisReviewDto>> getAllReviews() {
        return ResponseEntity.ok(thesisReviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisReviewDto> getReviewById(@PathVariable Long id) {
        return thesisReviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/thesis/{thesisId}")
    public ResponseEntity<Set<ThesisReviewDto>> getReviewsByThesis(@PathVariable Long thesisId) {
        return ResponseEntity.ok(thesisReviewService.findByThesisId(thesisId));
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<Set<ThesisReviewDto>> getReviewsByReviewer(@PathVariable Long reviewerId) {
        return ResponseEntity.ok(thesisReviewService.findByReviewerId(reviewerId));
    }

    @PostMapping
    public ResponseEntity<ThesisReviewDto> createReview(@RequestBody ThesisReviewDto reviewDto) {
        return ResponseEntity.ok(thesisReviewService.save(reviewDto));
    }

    @PostMapping("/submit")
    public ResponseEntity<ThesisReviewDto> submitReview(@RequestParam Long thesisId,
                                                       @RequestParam Long reviewerId,
                                                       @RequestParam String content,
                                                       @RequestParam boolean isPositive) {
        return ResponseEntity.ok(thesisReviewService.submitReview(thesisId, reviewerId, content, isPositive));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisReviewDto> updateReview(@PathVariable Long id, @RequestBody ThesisReviewDto reviewDto) {
        if (!thesisReviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reviewDto.setId(id);
        return ResponseEntity.ok(thesisReviewService.save(reviewDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!thesisReviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        thesisReviewService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
