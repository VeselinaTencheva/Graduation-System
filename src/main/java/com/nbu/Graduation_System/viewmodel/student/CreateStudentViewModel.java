package com.nbu.Graduation_System.viewmodel.student;

import com.nbu.Graduation_System.viewmodel.user.CreateUserViewModel;    

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateStudentViewModel extends CreateUserViewModel {
    @NotBlank
    // @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String name;

    @Min(value = 0, message = "Min 0")
    @Max(value = 18, message = "Max 18")
    private int ageAppropriateness;
}
