package com.nbu.Graduation_System.dto.student;

import com.nbu.Graduation_System.dto.user.CreateUserDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateStudentDto extends CreateUserDto {

    @NotNull(message = "Department is required")
    private Long departmentId;
}
