package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.ArticleDAO;
import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.ArticleDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;

    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<ArticleDTO> findAll() {
        return articleDAO.findAll().stream()
                .map(ArticleDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO findById(int id) throws BusinessException {
        Optional<Article> result = articleDAO.findById(id);
        if (result.isPresent()) {

            return ArticleDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.ARTICLE_NOT_FOUND);
        }
    }

    @Override
    public void save(ArticleDTO articleDTO) throws BusinessException {
        articleDAO.save(ArticleDTOHelper.toEntity(articleDTO));
    }

    @Override
    public void deleteById(int id) {

        articleDAO.deleteById(id);

    }
}
