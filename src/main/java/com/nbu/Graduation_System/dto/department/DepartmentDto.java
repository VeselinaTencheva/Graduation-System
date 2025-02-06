package com.nbu.Graduation_System.dto.department;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;

    @NotNull
    private DepartmentType type;

    @Size(min = 3, max = 50)
    @NotBlank
    private String description;

    @Size(min = 3, max = 50)
    @NotBlank
    private String contactEmail;
}
