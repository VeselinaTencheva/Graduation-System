package com.nbu.Graduation_System.dto.thesis;

import java.time.LocalDateTime;

import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;
import com.nbu.Graduation_System.dto.thesis_review.ThesisReviewDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"thesis", "student", "supervisor"})
@EqualsAndHashCode(exclude = {"thesis", "student", "supervisor"})
public class ThesisApplicationDto {

    private Long id;

    private LocalDateTime uploadDate;

    private String content;

    private ThesisDto thesis;

    private ThesisReviewDto review;
    
    private ThesisDefenseDto defense;
}
