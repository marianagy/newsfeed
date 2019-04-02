package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDAO extends JpaRepository<Article, Integer> {
}
