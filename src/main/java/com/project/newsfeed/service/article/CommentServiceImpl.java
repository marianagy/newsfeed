package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.CommentDAO;
import com.project.newsfeed.entity.article.Comment;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
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

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
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

        commentDAO.save(CommentDTOHelper.toEntity(commentDTO));
    }

    @Override
    public void deleteById(int id) {
        commentDAO.deleteById(id);
    }
}
