package com.nbu.Graduation_System.dto.thesis;

import java.time.LocalDateTime;

import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ThesisDto {

    private Long id;

    @NotNull
    private LocalDateTime uploadDate;

    @NotBlank
    // @Size(min = 5, max = 100, message="Min 5, Max 100")
    private String content;

    private ThesisApplicationDto thesisApplication;
    
    private ThesisReviewDto review;
    
    private ThesisDefenseDto defense;
}
