package com.project.newsfeed.service.article;

import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.ArticleListDTO;
import com.project.newsfeed.service.article.dto.CategoryDTO;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> findAll();

    ArticleDTO findById(int id) throws BusinessException;

    void save(ArticleDTO articleDTO) throws BusinessException;

    void deleteById(int id);

    List<ArticleDTO> getArticlesForUser(User user);

    ArticleListDTO getFilteredArticles(Integer pageIndex, Integer pageSize);

    ArticleListDTO getfilteredArticlesForUser(User user, Integer pageIndex, Integer pageSize);

    List<Tag> getTagsFromDTO(ArticleDTO articleDTO);

    ArticleListDTO getRecommendedArticlesForUser(User user, Integer pageIndex, Integer pageSize);

    ArticleListDTO getAllArticlesByCategory(CategoryDTO categoryDTO, Integer pageIndex, Integer pageSize);
}
