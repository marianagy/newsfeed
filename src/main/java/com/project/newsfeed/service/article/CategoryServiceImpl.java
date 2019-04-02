package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.CategoryDAO;
import com.project.newsfeed.entity.article.Category;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.CategoryDTO;
import com.project.newsfeed.service.article.dto.CategoryDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryDAO.findAll().stream()
                .map(CategoryDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(int id) throws BusinessException {
        Optional<Category> result = categoryDAO.findById(id);
        if (result.isPresent()) {

            return CategoryDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public void save(CategoryDTO categoryDTO) throws BusinessException {
        categoryDAO.save(CategoryDTOHelper.toEntity(categoryDTO));
    }

    @Override
    public void deleteById(int id) {

        categoryDAO.deleteById(id);

    }
}
