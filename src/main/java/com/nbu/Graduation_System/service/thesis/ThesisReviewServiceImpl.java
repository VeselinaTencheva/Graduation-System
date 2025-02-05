package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.repository.ThesisReviewRepository;
import com.nbu.Graduation_System.repository.ThesisRepository;
import com.nbu.Graduation_System.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ThesisReviewServiceImpl implements ThesisReviewService {
    
    private final ThesisReviewRepository thesisReviewRepository;
    private final ThesisRepository thesisRepository;
    private final TeacherRepository teacherRepository;

    public ThesisReviewServiceImpl(ThesisReviewRepository thesisReviewRepository,
                                 ThesisRepository thesisRepository,
                                 TeacherRepository teacherRepository) {
        this.thesisReviewRepository = thesisReviewRepository;
        this.thesisRepository = thesisRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public ThesisReview save(ThesisReview review) {
        return thesisReviewRepository.save(review);
    }

    @Override
    public Optional<ThesisReview> findById(Long id) {
        return thesisReviewRepository.findById(id);
    }

    @Override
    public List<ThesisReview> findAll() {
        return thesisReviewRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        thesisReviewRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return thesisReviewRepository.existsById(id);
    }

    @Override
    public Set<ThesisReview> findByThesis(Thesis thesis) {
        return thesisReviewRepository.findByThesis(thesis);
    }

    @Override
    public Set<ThesisReview> findByReviewer(Teacher reviewer) {
        return thesisReviewRepository.findByReviewer(reviewer);
    }

    @Override
    public ThesisReview submitReview(Long thesisId, Long reviewerId, String content, boolean isPositive) {
        Thesis thesis = thesisRepository.findById(thesisId)
            .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));
        
        Teacher reviewer = teacherRepository.findById(reviewerId)
            .orElseThrow(() -> new EntityNotFoundException("Reviewer not found"));

        ThesisReview review = new ThesisReview();
        review.setThesis(thesis);
        review.setReviewer(reviewer);
        review.setContent(content);
        review.setPositive(isPositive);
        review.setSubmissionDate(LocalDateTime.now());

        return thesisReviewRepository.save(review);
    }
}
