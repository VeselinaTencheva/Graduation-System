package com.nbu.Graduation_System.viewmodel.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DepartmentViewModel {

    private Long id;

    @NotNull
    private DepartmentType type;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String description;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String contactEmail;

    @NotNull
    private TeacherViewModel dean;

    private List<StudentViewModel> students;

    private List<TeacherViewModel> teachers;


}
