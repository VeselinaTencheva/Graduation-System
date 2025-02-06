package com.nbu.Graduation_System.viewmodel.teacher;

import com.nbu.Graduation_System.dto.department.DepartmentDto;
import com.nbu.Graduation_System.viewmodel.user.CreateUserViewModel;    

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateTeacherViewModel extends CreateUserViewModel {
   @NotBlank
    private String academicTitle;

    @NotNull
    private DepartmentDto department;
}
