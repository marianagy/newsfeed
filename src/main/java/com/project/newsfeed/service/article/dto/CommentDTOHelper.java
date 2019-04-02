package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Comment;

public class CommentDTOHelper {

    public CommentDTOHelper() {
    }

    public static CommentDTO fromEntity(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setDate(comment.getDate());
        commentDTO.setUser(comment.getUser());
        commentDTO.setArticle(comment.getArticle());

        return commentDTO;
    }

    public static Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setDate(commentDTO.getDate());
        comment.setUser(commentDTO.getUser());
        comment.setArticle(comment.getArticle());

        return comment;
    }
}
