package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.CommentDAO;
import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.article.Comment;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.ArticleDTOHelper;
import com.project.newsfeed.service.article.dto.CommentDTO;
import com.project.newsfeed.service.article.dto.CommentDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;
    private UserDAO userDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO, UserDAO userDAO) {
        this.commentDAO = commentDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<CommentDTO> findAll() {
        return commentDAO.findAll().stream()
                .map(CommentDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO findById(int id) throws BusinessException {
        Optional<Comment> result = commentDAO.findById(id);
        if (result.isPresent()) {

            return CommentDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.COMMENT_NOT_FOUND);
        }
    }

    @Override
    public void save(CommentDTO commentDTO) throws BusinessException {

        String username = commentDTO.getUser().getUsername();
        User user = userDAO.findByUsername(username);
        Comment comment = CommentDTOHelper.toEntity(commentDTO);
        comment.setUser(user);
        commentDAO.save(comment);
    }

    @Override
    public void deleteById(int id) {
        commentDAO.deleteById(id);
    }

    @Override
    public List<CommentDTO> getCommentsForArticle(ArticleDTO articleDTO) {
        return commentDAO.getCommentsForArticle(ArticleDTOHelper.toEntity(articleDTO)).stream()
                .map(CommentDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }
}
