package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.ThesisDefense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {ThesisMapper.class, TeacherMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThesisDefenseMapper {
    ThesisDefenseDto toDto(ThesisDefense entity);
    ThesisDefense toEntity(ThesisDefenseDto dto);
}
