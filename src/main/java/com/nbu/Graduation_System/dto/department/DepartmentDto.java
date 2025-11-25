package com.nbu.Graduation_System.dto.department;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class DepartmentDto {
    private Long id;

    @NotNull
    private DepartmentType type;

    @NotBlank
    private String description;

    @NotBlank
    private String contactEmail;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeacherDto dean;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeacherDto> teachers;
}
