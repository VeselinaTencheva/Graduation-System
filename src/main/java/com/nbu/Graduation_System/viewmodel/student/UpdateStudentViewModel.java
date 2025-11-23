package com.nbu.Graduation_System.viewmodel.student;

import com.nbu.Graduation_System.viewmodel.user.UpdateUserViewModel;    

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentViewModel extends UpdateUserViewModel {
    @NotNull(message = "Department is required")
    private Long departmentId;
}
