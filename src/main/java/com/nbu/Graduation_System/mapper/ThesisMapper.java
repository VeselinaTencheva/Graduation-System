package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.ThesisDto;
import com.nbu.Graduation_System.entity.Thesis;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {ThesisApplicationMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThesisMapper {
    ThesisDto toDto(Thesis entity);
    Thesis toEntity(ThesisDto dto);
}
