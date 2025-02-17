package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_review.CreateThesisReviewDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;
import java.util.List;

public interface ThesisReviewService {
    ThesisReviewDto save(CreateThesisReviewDto review);
    ThesisReviewDto update(Long id, CreateThesisReviewDto review);
    ThesisReviewDto findById(Long id);
    ThesisReviewDto findByThesisId(Long thesisId);
    List<ThesisReviewDto> findAll();
    void deleteById(Long id);
    // boolean existsById(Long id);
    // Set<ThesisReviewDto> findByThesisId(Long thesisId);
    // Set<ThesisReviewDto> findByReviewerId(Long reviewerId);
    // ThesisReviewDto submitReview(Long thesisId, Long reviewerId, String content, boolean isPositive);
}
