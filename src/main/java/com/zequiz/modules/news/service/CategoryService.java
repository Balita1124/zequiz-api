package com.zequiz.modules.news.service;

import com.zequiz.modules.news.dto.CategoryRequest;
import com.zequiz.modules.news.model.Category;
import com.zequiz.modules.news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        return categoryRepository.findAll(paging);
    }

    public Category create(CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.getCode(),
                categoryRequest.getLabel(), categoryRequest.getDescription(), categoryRequest.getWebsite());
        return categoryRepository.save(category);
    }

    public Category update(Category currentCategory, CategoryRequest categoryRequest) {
        currentCategory.setCode(categoryRequest.getCode());
        currentCategory.setLabel(categoryRequest.getLabel());
        currentCategory.setLabel(categoryRequest.getLabel());
        currentCategory.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(currentCategory);

    }

    public Category findCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
