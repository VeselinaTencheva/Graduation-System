package com.nbu.Graduation_System.viewmodel.teacher;

import com.nbu.Graduation_System.dto.department.DepartmentDto;
import com.nbu.Graduation_System.viewmodel.user.UserViewModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeacherViewModel extends UserViewModel{
    
    @NotBlank
    private String academicTitle;

    @NotNull
    private DepartmentDto department;
}
