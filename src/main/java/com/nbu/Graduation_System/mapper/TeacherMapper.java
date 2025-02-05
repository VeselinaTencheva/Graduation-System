package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.TeacherDto;
import com.nbu.Graduation_System.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {
    TeacherDto toDto(Teacher entity);
    Teacher toEntity(TeacherDto dto);
}
