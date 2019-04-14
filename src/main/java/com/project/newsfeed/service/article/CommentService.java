package com.project.newsfeed.service.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> findAll();

    CommentDTO findById(int id) throws BusinessException;

    void save(CommentDTO commentDTO) throws BusinessException;

    void deleteById(int id);

    List<CommentDTO> getCommentsForArticle(ArticleDTO articleDTO);
}
