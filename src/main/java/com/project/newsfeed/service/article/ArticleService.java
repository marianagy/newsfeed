package com.project.newsfeed.service.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> findAll();

    ArticleDTO findById(int id) throws BusinessException;

    void save(ArticleDTO profile) throws BusinessException;

    void deleteById(int id);

}
