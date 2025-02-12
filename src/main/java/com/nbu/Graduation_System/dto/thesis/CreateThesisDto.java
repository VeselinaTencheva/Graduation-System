package com.nbu.Graduation_System.dto.thesis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateThesisDto {
    private String content;
    private Long applicationId;
}
