package com.zequiz.modules.news.controller;

import com.zequiz.modules.news.dto.CategoryListResponse;
import com.zequiz.modules.news.dto.CategoryRequest;
import com.zequiz.modules.news.model.Category;
import com.zequiz.modules.news.service.CategoryService;
import com.zequiz.shared.utils.ErrorSection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "categories", name = "Find All categories paginated")
    public ResponseEntity<CategoryListResponse> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10000") Integer size,
            @RequestParam(defaultValue = "code") String sort
    ) {
        return new ResponseEntity<>(new CategoryListResponse(categoryService.getAll(page, size, sort)), HttpStatus.OK);
    }

    @GetMapping(value = "categories/{id}", name = "Find category")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Integer id) {
        return new ResponseEntity<Category>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @PostMapping(value = "categories", name = "Create category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid final CategoryRequest categoryRequest, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorSection es = new ErrorSection(categoryRequest, bindingResult.getAllErrors());
            return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                categoryService.create(categoryRequest),
                HttpStatus.CREATED
        );
    }

//    @PutMapping(value = "categorys/{categoryId}", name = "Update category")
//    public ApiResponse updateCategory(@PathVariable(value = "categoryId") Integer categoryId, @RequestBody @Valid CategoryRequest categoryRequest, BindingResult bindingResult) {
//
//        Category currentCategory = categoryService.findCategoryById(categoryId);
//        if (currentCategory == null) {
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "Category with id = " + categoryId + " not found",
//                    new ErrorSection(categoryRequest, null)
//            );
//        }
//        if (bindingResult.hasErrors()) {
//            ErrorSection es = new ErrorSection(categoryRequest, bindingResult.getAllErrors());
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "Category not updated",
//                    es
//            );
//        }
//        Category category = categoryService.update(currentCategory, categoryRequest);
//        return new ApiResponse(
//                true,
//                HttpStatus.OK,
//                "Category Updated successfully",
//                category
//        );
//    }
//
//    @DeleteMapping(value = "categorys/{categoryId}", name = "Delete category")
//    public ApiResponse deleteCategory(@PathVariable(value = "categoryId") Integer categoryId) {
//        Category currentCategory = categoryService.findCategoryById(categoryId);
//        if (currentCategory == null) {
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "Category with id = " + categoryId + " not found",
//                    null
//            );
//        }
//        categoryService.delete(currentCategory);
//        return new ApiResponse(
//                true,
//                HttpStatus.OK,
//                "Category Deleted successfully",
//                null
//        );
//    }
}
