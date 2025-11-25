package com.nbu.Graduation_System.dto.department;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
public class DepartmentDto {
    private Long id;

    @NotNull
    private DepartmentType type;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String description;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String contactEmail;

    @ToString.Exclude
    private TeacherDto dean;
}
