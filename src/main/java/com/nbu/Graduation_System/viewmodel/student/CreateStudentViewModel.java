package com.nbu.Graduation_System.viewmodel.student;

import com.nbu.Graduation_System.viewmodel.user.CreateUserViewModel;    

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentViewModel extends CreateUserViewModel {
    @NotNull(message = "Department is required")
    private Long departmentId;
}
