package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDAO extends JpaRepository<Article, Integer> {

    @Query("Select a FROM Article a where a.user = ?1")
    List<Article> getArticlesForUser(User user);
}
