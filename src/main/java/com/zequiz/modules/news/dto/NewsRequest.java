package com.zequiz.modules.news.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class NewsRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
