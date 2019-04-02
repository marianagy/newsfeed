package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Category;

public class CategoryDTOHelper {

    public CategoryDTOHelper() {
    }

    public static CategoryDTO fromEntity(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setCategoryArticleList(category.getCategoryArticleList());

        return categoryDTO;
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setCategoryArticleList(category.getCategoryArticleList());

        return category;
    }
}
