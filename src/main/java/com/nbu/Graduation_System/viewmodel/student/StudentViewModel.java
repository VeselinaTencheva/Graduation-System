package com.nbu.Graduation_System.viewmodel.student;

import com.nbu.Graduation_System.viewmodel.department.DepartmentViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;
import com.nbu.Graduation_System.viewmodel.user.UserViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentViewModel extends UserViewModel {

    @NotBlank // add validation for a positive number
    private String facultyNumber;

    @NotNull
    private DepartmentViewModel department;

    private ThesisApplicationViewModel thesisApplication;
}
