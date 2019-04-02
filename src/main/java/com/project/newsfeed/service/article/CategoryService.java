package com.project.newsfeed.service.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(int id) throws BusinessException;

    void save(CategoryDTO categoryDTO) throws BusinessException;

    void deleteById(int id);
}
