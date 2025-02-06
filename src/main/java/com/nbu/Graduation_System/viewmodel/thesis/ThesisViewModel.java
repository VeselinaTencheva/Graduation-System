package com.nbu.Graduation_System.viewmodel.thesis;

import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ThesisViewModel {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull(message = "Thesis application is required")
    private ThesisApplicationViewModel thesisApplication;

    @NotNull(message = "Supervisor is required")
    private TeacherViewModel supervisor;

    @NotNull(message = "Status is required")
    private String status;

    @NotNull(message = "Submission date is required")
    private LocalDateTime submissionDate;

    public StudentViewModel getStudent() {
        return thesisApplication != null ? thesisApplication.getStudent() : null;
    }
}
