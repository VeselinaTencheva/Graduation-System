package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisReviewDto;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ThesisReviewService {
    ThesisReviewDto save(ThesisReviewDto review);
    Optional<ThesisReviewDto> findById(Long id);
    List<ThesisReviewDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Set<ThesisReviewDto> findByThesisId(Long thesisId);
    Set<ThesisReviewDto> findByReviewerId(Long reviewerId);
    ThesisReviewDto submitReview(Long thesisId, Long reviewerId, String content, boolean isPositive);
}
