package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.ArticleDAO;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.dto.ArticleDTO;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;

    @Override
    public List<ArticleDTO> findAll() {
        return null;
    }

    @Override
    public ArticleDTO findById(int id) throws BusinessException {
        return null;
    }

    @Override
    public void save(ArticleDTO profile) throws BusinessException {

    }

    @Override
    public void deleteById(int id) {

    }
}
