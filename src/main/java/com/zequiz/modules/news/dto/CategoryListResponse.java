package com.zequiz.modules.news.dto;

import com.zequiz.modules.news.model.Category;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class CategoryListResponse {

    private final Page<Category> categoriesPage;

    public CategoryListResponse(Page<Category> categoriesPage) {
        this.categoriesPage = categoriesPage;
    }

}
