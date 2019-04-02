package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
}
