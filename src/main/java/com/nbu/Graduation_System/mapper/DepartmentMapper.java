package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.DepartmentDto;
import com.nbu.Graduation_System.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentDto toDto(Department entity);
    Department toEntity(DepartmentDto dto);
}
