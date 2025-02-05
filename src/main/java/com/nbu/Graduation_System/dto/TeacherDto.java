package com.nbu.Graduation_System.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherDto extends UserDto {
    private String academicTitle;
}
