package com.zequiz.modules.news.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {
    @NotBlank
    private String code;

    @NotBlank
    private String label;

    private String description;

    private String website;
}
