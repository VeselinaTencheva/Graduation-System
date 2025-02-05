package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.Teacher;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ThesisReviewService {
    ThesisReview save(ThesisReview review);
    Optional<ThesisReview> findById(Long id);
    List<ThesisReview> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Set<ThesisReview> findByThesis(Thesis thesis);
    Set<ThesisReview> findByReviewer(Teacher reviewer);
    ThesisReview submitReview(Long thesisId, Long reviewerId, String content, boolean isPositive);
}
