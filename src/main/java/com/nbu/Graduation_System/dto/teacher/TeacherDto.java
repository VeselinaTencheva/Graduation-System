package com.nbu.Graduation_System.dto.teacher;

import com.nbu.Graduation_System.dto.department.DepartmentDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;

import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = {"department", "supervisedTheses", "reviews"})
@EqualsAndHashCode(exclude = {"department", "supervisedTheses", "reviews"})
public class TeacherDto {
    private Long id;
    private String name;
    private String email;
    private String academicTitle;
    private DepartmentDto department;
    private List<ThesisApplicationDto> supervisedTheses;
    private List<ThesisReviewDto> reviews;
}

