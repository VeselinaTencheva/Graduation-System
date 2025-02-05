package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisReviewDto;
import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.mapper.ThesisReviewMapper;
import com.nbu.Graduation_System.repository.ThesisReviewRepository;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.repository.ThesisRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThesisReviewServiceImpl implements ThesisReviewService {
    
    private final ThesisReviewRepository thesisReviewRepository;
    private final ThesisRepository thesisRepository;
    private final TeacherRepository teacherRepository;
    private final ThesisReviewMapper thesisReviewMapper;

    public ThesisReviewServiceImpl(ThesisReviewRepository thesisReviewRepository,
                                 ThesisRepository thesisRepository,
                                 TeacherRepository teacherRepository,
                                 ThesisReviewMapper thesisReviewMapper) {
        this.thesisReviewRepository = thesisReviewRepository;
        this.thesisRepository = thesisRepository;
        this.thesisReviewMapper = thesisReviewMapper;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public ThesisReviewDto save(ThesisReviewDto reviewDto) {
        ThesisReview review = thesisReviewMapper.toEntity(reviewDto);
        review = thesisReviewRepository.save(review);
        return thesisReviewMapper.toDto(review);
    }

    @Override
    public Optional<ThesisReviewDto> findById(Long id) {
        return thesisReviewRepository.findById(id)
                .map(thesisReviewMapper::toDto);
    }

    @Override
    public List<ThesisReviewDto> findAll() {
        return thesisReviewRepository.findAll().stream()
                .map(thesisReviewMapper::toDto)
                .collect(Collectors.toList());
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
    public Set<ThesisReviewDto> findByThesisId(Long thesisId) {
        Thesis thesis = thesisRepository.findById(thesisId)
            .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));
            
        return thesisReviewRepository.findByThesis(thesis).stream()
            .map(thesisReviewMapper::toDto)
            .collect(Collectors.toSet());
    }

    @Override
    public Set<ThesisReviewDto> findByReviewerId(Long reviewerId) {
        Teacher reviewer = teacherRepository.findById(reviewerId)
            .orElseThrow(() -> new EntityNotFoundException("Reviewer not found"));
        return thesisReviewRepository.findByReviewer(reviewer).stream()
        .map(thesisReviewMapper::toDto)
        .collect(Collectors.toSet());
    }

    @Override
    public ThesisReviewDto submitReview(Long thesisId, Long reviewerId, String content, boolean isPositive) {
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

        review = thesisReviewRepository.save(review);
        return thesisReviewMapper.toDto(review);
    }
}
