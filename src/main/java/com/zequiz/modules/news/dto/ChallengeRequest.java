package com.zequiz.modules.news.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ChallengeRequest {
    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    private Integer categoryId;

    private Integer level;
}
