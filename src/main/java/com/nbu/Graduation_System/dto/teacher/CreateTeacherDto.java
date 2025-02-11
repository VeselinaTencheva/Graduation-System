package com.nbu.Graduation_System.dto.teacher;

import com.nbu.Graduation_System.dto.user.CreateUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTeacherDto extends CreateUserDto {
    @NotNull(message = "Department is required")
    private Long departmentId;

    @NotBlank
    private String academicTitle;
}
