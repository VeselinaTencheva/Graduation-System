package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_review.CreateThesisReviewDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;
import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.repository.ThesisReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ThesisReviewServiceImpl implements ThesisReviewService {
    
    private final ThesisReviewRepository thesisReviewRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ThesisReviewDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                thesisReviewRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ThesisReview with id=" + id + " not found!")),
                ThesisReviewDto.class);
    }

    @Override
    public ThesisReviewDto findByThesisId(Long thesisId) {
        return mapperUtil.getModelMapper().map(
                thesisReviewRepository.findByThesisId(thesisId)
                        .orElseThrow(() -> new RuntimeException("ThesisReview with thesisId=" + thesisId + " not found!")),
                ThesisReviewDto.class);
    }

    @Override
    public List<ThesisReviewDto> findAll() {
        return mapperUtil.mapList(thesisReviewRepository.findAll(), ThesisReviewDto.class);
    }

    @Override
    public ThesisReviewDto save(CreateThesisReviewDto reviewDto) {
        ThesisReview review = mapperUtil.getModelMapper().map(reviewDto, ThesisReview.class);
        review.setReviewDate(LocalDateTime.now());
        review = thesisReviewRepository.save(review);
        return mapperUtil.getModelMapper().map(review, ThesisReviewDto.class);
    }

    @Override
    public ThesisReviewDto update(Long id, CreateThesisReviewDto reviewDto) {
        ThesisReview review = thesisReviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ThesisReview with id=" + id + " not found!"));
        
        review.setComments(reviewDto.getComments());
        review.setPositive(reviewDto.isPositive());
        review.setReviewDate(LocalDateTime.now());
        
        review = thesisReviewRepository.save(review);
        return mapperUtil.getModelMapper().map(review, ThesisReviewDto.class);
    }

    @Override
    public void deleteById(Long id) {
        ThesisReview review = thesisReviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ThesisReview with id=" + id + " not found!"));
        
        Thesis thesis = review.getThesis();
        if (thesis != null) {
            thesis.setReview(null);
        }
        review.setThesis(null);
        
        thesisReviewRepository.delete(review);
    }

    // @Override
    // public boolean existsById(Long id) {
    //     return thesisReviewRepository.existsById(id);
    // }

    // @Override
    // public Set<ThesisReviewDto> findByReviewerId(Long reviewerId) {
    //     Teacher reviewer = teacherRepository.findById(reviewerId)
    //         .orElseThrow(() -> new EntityNotFoundException("Reviewer not found"));
    //     return thesisReviewRepository.findByReviewer(reviewer).stream()
    //     .map(thesisReviewMapper::toDto)
    //     .collect(Collectors.toSet());
    // }

    // @Override
    // public ThesisReviewDto submitReview(Long thesisId, Long reviewerId, String content, boolean isPositive) {
    //     Thesis thesis = thesisRepository.findById(thesisId)
    //         .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));
        
    //     Teacher reviewer = teacherRepository.findById(reviewerId)
    //         .orElseThrow(() -> new EntityNotFoundException("Reviewer not found"));
        
    //     ThesisReview review = new ThesisReview();
    //     review.setThesis(thesis);
    //     review.setReviewer(reviewer);
    //     review.setContent(content);
    //     review.setPositive(isPositive);
    //     review.setSubmissionDate(LocalDateTime.now());

    //     review = thesisReviewRepository.save(review);
    //     return thesisReviewMapper.toDto(review);
    // }
}
