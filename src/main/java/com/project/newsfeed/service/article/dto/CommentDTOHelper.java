package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Comment;
import com.project.newsfeed.service.user.dto.UserDTOHelper;

public class CommentDTOHelper {

    public CommentDTOHelper() {
    }

    public static CommentDTO fromEntity(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setDate(comment.getDate());
        commentDTO.setUser((UserDTOHelper.fromEntity(comment.getUser())));
        commentDTO.setArticle(ArticleDTOHelper.fromEntity(comment.getArticle()));

        return commentDTO;
    }

    public static Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setDate(commentDTO.getDate());
        comment.setUser(UserDTOHelper.toEntity(commentDTO.getUser()));
        comment.setArticle(ArticleDTOHelper.toEntity(commentDTO.getArticle()));

        return comment;
    }
}
