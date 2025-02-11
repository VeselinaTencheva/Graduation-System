package com.nbu.Graduation_System.viewmodel.teacher;

import com.nbu.Graduation_System.viewmodel.user.CreateUserViewModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class CreateTeacherViewModel extends CreateUserViewModel {
    @NotNull(message = "Department is required")
    private Long departmentId;

    @NotBlank
    private String academicTitle;
}
