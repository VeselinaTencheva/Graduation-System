package com.nbu.Graduation_System.mapper;

import com.nbu.Graduation_System.dto.UserDto;
import com.nbu.Graduation_System.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User entity);
    User toEntity(UserDto dto);
}
