package com.nbu.Graduation_System.dto.thesis;

import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ThesisDto {

    private Long id;

    @NotBlank
    // @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String title;

    @NotBlank
    // @Size(min = 5, max = 100, message="Min 5, Max 100")
    private String content;

    private ThesisApplicationDto thesisApplication;
    
    private ThesisReviewDto review;
    
    private ThesisDefenseDto defense;
}
