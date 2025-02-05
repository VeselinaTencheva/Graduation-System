package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.StudentDto;
import com.nbu.Graduation_System.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    StudentDto toDto(Student entity);
    Student toEntity(StudentDto dto);
}
