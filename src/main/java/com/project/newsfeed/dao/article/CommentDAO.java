package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.article.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {

    @Query("Select c FROM Comment c where c.article = ?1")
    List<Comment> getCommentsForArticle(Article article);

}
