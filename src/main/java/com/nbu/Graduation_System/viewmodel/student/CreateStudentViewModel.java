package com.nbu.Graduation_System.viewmodel.student;

import com.nbu.Graduation_System.viewmodel.department.DepartmentViewModel;
import com.nbu.Graduation_System.viewmodel.user.CreateUserViewModel;    

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateStudentViewModel extends CreateUserViewModel {
    @NotBlank // add validation for a positive number
    private String facultyNumber;

    @NotNull
    private DepartmentViewModel department;
}
