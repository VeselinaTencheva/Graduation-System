package com.nbu.Graduation_System.viewmodel.department;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.viewmodel.user.CreateUserViewModel;    

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateDepartmentViewModel extends CreateUserViewModel {

    @NotNull
    private DepartmentType type;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String description;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String contactEmail;
}
