package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.ThesisReviewDto;
import com.nbu.Graduation_System.entity.ThesisReview;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {ThesisMapper.class, TeacherMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThesisReviewMapper {
    ThesisReviewDto toDto(ThesisReview entity);
    ThesisReview toEntity(ThesisReviewDto dto);
}
