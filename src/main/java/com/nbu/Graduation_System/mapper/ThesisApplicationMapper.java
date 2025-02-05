package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.ThesisApplicationDto;
import com.nbu.Graduation_System.entity.ThesisApplication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThesisApplicationMapper {
    ThesisApplicationDto toDto(ThesisApplication entity);
    ThesisApplication toEntity(ThesisApplicationDto dto);
}
